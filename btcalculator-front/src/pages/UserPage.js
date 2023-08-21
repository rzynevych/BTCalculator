import React from 'react';
import { server_url } from '../utils/config';
import s from './UserPage.module.css';
import { withRouter } from '../utils/withRouter';
import { Link } from 'react-router-dom';
import Receipt from './Receipt';

class UserPage extends React.Component {
    constructor(props) {
        super(props);
        this.startDateInputHandler = this.startDateInputHandler.bind(this);
        this.endDateInputHandler = this.endDateInputHandler.bind(this);
        this.formDaysList = this.formDaysList.bind(this);
        this.checkboxHandler = this.checkboxHandler.bind(this);
        this.receiptTypeSelectHandler = this.receiptTypeSelectHandler.bind(this);
        this.receiptAmountInputHandler = this.receiptAmountInputHandler.bind(this);
        this.receiptRemoveHandler = this.receiptRemoveHandler.bind(this);
        this.addReceipt = this.addReceipt.bind(this);
        this.distanceInputHandler = this.distanceInputHandler.bind(this);
        this.errorMessage = this.errorMessage.bind(this);
        this.sendRequest = this.sendRequest.bind(this);
        this.state = {
            tripStartDate: '',
            tripEndDate: '',
            error : false,
            error_message : '',
            distance: 0,
            calculated: false,
            result: ''
        }
        this.days = [];
        this.receiptTypes = [];
        this.receipts = [];
    }

    componentDidMount() {
        let url = new URL(server_url + '/getReceiptTypes');
        fetch(url,
            {
                method: "GET"
            }).then(response => response.json()).then(json => {
            this.receiptTypes = json;
            this.receiptTypes.push({
                type: 'Not selected'
            });
            this.forceUpdate();

        }).catch(console.log);
    }

    startDateInputHandler(e) {
        this.setState({
            tripStartDate: e.target.value
        }, this.formDaysList)
    }

    endDateInputHandler(e) {
        this.setState({
            tripEndDate: e.target.value
        }, this.formDaysList)
    }

    errorMessage(message) {
        this.setState({
            error : true,
            error_message : message
        })
    }

    formDaysList() {
        let start = new Date(this.state.tripStartDate).getTime();
        let end = new Date(this.state.tripEndDate).getTime();

        if (isNaN(start) || isNaN(end)) {
            this.errorMessage('Invalid date');
            return ;
        }

        if  ((end - start) / 86400000 > 365) {
            this.errorMessage('Trip duration is more than 365 days');
            return ;
        }

        if (end < start) {
            this.errorMessage('End date must be after start date');
            return ;
        }

        this.days = [];
        while (start < end + 86400000) {
            this.days.push({
                date: new Date(start).toUTCString().substring(5, 16),
                disabled : false
            })
            start += 86400000;
        }

        this.setState({
            error : false,
            error_message : '',
        }, () => console.log(this.days))
    }

    checkboxHandler(e) {
        this.days[e.target.value].disabled = !this.days[e.target.value].disabled;
        console.log(this.days);
    }

    receiptTypeSelectHandler(e, index) {
        this.receipts[index].type = e.target.value;
        console.log(this.receipts);
        this.forceUpdate();
    }

    receiptAmountInputHandler(e, index) {
        this.receipts[index].amount = e.target.value.replace(/[^0-9]/g, '')
            .replace(/0*(?=[1-9]{1,})/g, '').replace(/(?<=[0-9]{7}).*/g, '');
        if (this.receipts[index].amount === '')
            this.receipts[index].amount = '0';
        this.forceUpdate();
    }

    receiptCentAmountInputHandler(e, index) {
        this.receipts[index].centAmount = e.target.value.replace(/[^0-9]/g, '')
            .replace(/0*(?=[1-9]{1,})/g, '').replace(/(?<=[0-9]{2}).*/g, '');
        if (this.receipts[index].centAmount === '')
            this.receipts[index].centAmount = '00';
        this.forceUpdate();
    }

    receiptRemoveHandler(index) {
        this.receipts.splice(index, 1);
        this.forceUpdate();
    }

    addReceipt() {
        this.receipts.push({
            type: 'Not selected',
            amount: '0',
            centAmount: '00'
        });
        this.forceUpdate();
    }

    distanceInputHandler(e) {
        this.setState({
            distance: e.target.value
        })
    }


    sendRequest() {

        let ready = true;
        if (this.days.length === 0)
            ready = false;
        this.receipts.forEach(rcp => { 
            if (rcp.type === 'Not selected' || (+rcp.amount === 0 && +rcp.centAmount === 0))
                ready = false; 
        });
        if (!ready) {
            this.errorMessage('Some of fields are not filled. Please check your input');
            return ;
        }

        if (this.state.calculated) {
            this.receipts = [];
            this.days = [];
            this.setState({
                tripStartDate: '',
                tripEndDate: '',
                error: false,
                errorMessage: '',
                distance: 0,
                calculated: false,
                result: ''
            });
            return;
        }
        let payload = {
            tripStartDate: this.state.tripStartDate,
            tripEndDate: this.state.tripEndDate,
            tripDays: this.days,
            receipts: this.receipts,
            drivenDistance: this.state.distance
        }
        console.log(JSON.stringify(payload));
        let url = new URL(server_url + '/calculateReimbursement');
        fetch(url,
            {
                method: "POST",
                body: JSON.stringify(payload)
            }).then(response => response.json()).then(json => {
                console.log(json);
            this.setState({
                calculated: true,
                result: json.totalReimbursementAmount
            });
        }).catch(console.log);
    }

    render() {
        return (
            <div className={s.page_container}>
                <h1>BTCalculator</h1>
                <div>
                <label className={s.date_input__label}>
                    Enter the start date of the trip:
                    <input className={s.date_input__input} type='date' value={this.state.tripStartDate} onChange={this.startDateInputHandler}/>
                </label>
                <label className={s.date_input__label}>
                    Enter the end date of the trip:
                    <input className={s.date_input__input} type='date' value={this.state.tripEndDate} onChange={this.endDateInputHandler}/>
                </label>
                </div>
                <div className={s.error_message} 
                    style={{visibility : this.state.error ? 'visible' : 'hidden'}}>
                        {this.state.error_message}</div>
                <p>List of days:</p> 
                <div className={s.days_list}>
                    {this.days.map((day, index) => 
                        <div key={index}>
                            <input type='checkbox' value={index} defaultChecked={true} onChange={this.checkboxHandler}/>
                            {day.date}
                        </div>
                    )}
                </div>
                <p>Receipts:</p> 
                <div className={s.receipt_list}>
                    {this.receipts.map((receipt, index) => 
                        <Receipt
                            key={index}
                            receiptTypes={this.receiptTypes}
                            typeValue={receipt.type}
                            amountValue={receipt.amount}
                            centAmountValue={receipt.centAmount}
                            typeSelectHandler={(e) => this.receiptTypeSelectHandler(e, index)}
                            amountInputHandler={(e) => this.receiptAmountInputHandler(e, index)}
                            centAmountInputHandler={(e) => this.receiptCentAmountInputHandler(e, index)}
                            removeHandler={(e) => this.receiptRemoveHandler(index)}
                        />
                    )}  
                </div>
                <button onClick={this.addReceipt}>Add receipt</button>
                <p>Driven distance:</p>
                <input className={s.distance_input} type='number' value={this.state.distance} onChange={this.distanceInputHandler}/>
                <span>km</span>
                <div className={s.result_field__container}>Total reimbursement amount:
                    <span className={s.result_field}>{this.state.result}</span>$
                </div>
                <button className={s.calculateButton} onClick={this.sendRequest}>
                    {this.state.calculated ? 'Create new reimbursement' : 'Calculate reimbursement'}</button>
                <Link className={s.back_link} to='/'>Back</Link>
            </div>
        )
    }
} export default withRouter(UserPage);