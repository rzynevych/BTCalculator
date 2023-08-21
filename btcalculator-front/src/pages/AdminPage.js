import React from 'react';
import { server_url } from '../utils/config';
import s from './AdminPage.module.css';
import { withRouter } from '../utils/withRouter';
import { Link } from 'react-router-dom';
import ReceiptType from './ReceiptType';

class AdminPage extends React.Component {
    constructor(props) {
        super(props);

        this.addReceiptType = this.addReceiptType.bind(this);
        this.amountInputHandlerLeft = this.amountInputHandlerLeft.bind(this);
        this.amountInputHandlerRight = this.amountInputHandlerRight.bind(this);
        this.sendRequest = this.sendRequest.bind(this);
        this.receiptTypes = [];
        this.amounts = {
            dailyAllowance: {
                intPart: '0',
                centPart: '00'
            },
            mileageAllowance: {
                intPart: '0',
                centPart: '00'
            },
            mileageReimbursementLimit: {
                intPart: '0',
                centPart: '00'
            },
            totalReimbursementLimit: {
                intPart: '0',
                centPart: '00'
            }
        };
    }

    componentDidMount() {
        let url = new URL(server_url + '/getApplicationData');
        fetch(url,
            {
                method: "GET"
            }).then(response => response.json()).then(json => {
            this.receiptTypes = json.receiptTypes;
            this.amounts = {
                dailyAllowance: json.dailyAllowance,
                mileageAllowance: json.mileageAllowance,
                mileageReimbursementLimit: json.mileageReimbursementLimit,
                totalReimbursementLimit: json.totalReimbursementLimit
            };
            console.log(this.amounts);
            this.forceUpdate();

        }).catch(console.log);
    }

    receiptNameInputHandler(e, index) {
        this.receiptTypes[index].name = e.target.value;
        this.forceUpdate();
    }

    receiptLimitInputHandlerLeft(e, index) {
        this.receiptTypes[index].limit.intPart = e.target.value.replace(/[^0-9]/g, '')
            .replace(/0*(?=[1-9]{1,})/g, '').replace(/(?<=[0-9]{7}).*/g, '');
        this.forceUpdate();
    }

    receiptLimitInputHandlerRight(e, index) {
        this.receiptTypes[index].limit.centPart = e.target.value.replace(/[^0-9]/g, '')
            .replace(/0*(?=[1-9]{1,})/g, '').replace(/(?<=[0-9]{2}).*/g, '');
        this.forceUpdate();
    }

    receiptRemoveHandler(index) {
        this.receiptTypes.splice(index, 1);
        this.forceUpdate();
    }

    addReceiptType() {
        this.receiptTypes.push({
            name: '',
            limit: {
                intPart: '0',
                centPart: '00'
            }
        });
        this.forceUpdate();
    }

    amountInputHandlerLeft(e, name) {
        this.amounts[name].intPart = e.target.value.replace(/[^0-9]/g, '')
            .replace(/0*(?=[1-9]{1,})/g, '').replace(/(?<=[0-9]{7}).*/g, '');
        this.forceUpdate();
    }

    amountInputHandlerRight(e, name) {
        this.amounts[name].centPart = e.target.value.replace(/[^0-9]/g, '')
            .replace(/0*(?=[1-9]{1,})/g, '').replace(/(?<=[0-9]{2}).*/g, '');
        this.forceUpdate();
    }

    sendRequest() {
        let payload = {
            dailyAllowance: this.amounts.dailyAllowance,
            mileageAllowance: this.amounts.mileageAllowance,
            mileageReimbursementLimit: this.amounts.mileageReimbursementLimit,
            totalReimbursementLimit: this.amounts.totalReimbursementLimit,
            receiptTypes: this.receiptTypes
        };
        console.log(JSON.stringify(payload));
        let url = new URL(server_url + '/setApplicationData');
        fetch(url,
            {
                method: "POST",
                body: JSON.stringify(payload)
            }).then(response => response.json()).then(json => {
                console.log(json);
        }).catch(console.log);
    }

    render() {
        return (
            <div className={s.page_container}>
                <h1>BTCalculator</h1>
                <p>Receipt types:</p>
                <div className={s.receipt_type_list}>
                    {this.receiptTypes.map((receiptType, index) =>
                        <ReceiptType
                            key={index}
                            name={receiptType.name}
                            limit={receiptType.limit}
                            nameInputHandler={(e) => this.receiptNameInputHandler(e, index)}
                            limitInputHandlerLeft={(e) => this.receiptLimitInputHandlerLeft(e, index)}
                            limitInputHandlerRight={(e) => this.receiptLimitInputHandlerRight(e, index)}
                            removeHandler={(e) => this.receiptRemoveHandler(index)}
                        />
                    )}
                </div>
                <button onClick={this.addReceiptType}>Add receipt type</button>
                <label className={s.amount_input_label}>
                    Daily allowance:
                    <input className={s.amount_input_left} 
                        onChange={e => this.amountInputHandlerLeft(e, 'dailyAllowance')}
                        value={this.amounts.dailyAllowance.intPart}
                    />.
                    <input className={s.amount_input_right} 
                        onChange={e => this.amountInputHandlerRight(e, 'dailyAllowance')}
                        value={this.amounts.dailyAllowance.centPart}
                    />
                </label>
                <label className={s.amount_input_label}>
                    Mileage allowance:
                    <input className={s.amount_input_left} 
                        onChange={e => this.amountInputHandlerLeft(e, 'mileageAllowance')}
                        value={this.amounts.mileageAllowance.intPart}
                    />.
                    <input className={s.amount_input_right} 
                        onChange={e => this.amountInputHandlerRight(e, 'mileageAllowance')}
                        value={this.amounts.mileageAllowance.centPart}
                    />
                </label>
                <label className={s.amount_input_label}>
                    Mileage reimbursement limit:
                    <input className={s.amount_input_left} 
                        onChange={e => this.amountInputHandlerLeft(e, 'mileageReimbursementLimit')}
                        value={this.amounts.mileageReimbursementLimit.intPart}
                    />.
                    <input className={s.amount_input_right} 
                        onChange={e => this.amountInputHandlerRight(e, 'mileageReimbursementLimit')}
                        value={this.amounts.mileageReimbursementLimit.centPart}
                    />
                </label>
                <label className={s.amount_input_label}>
                    Total reimbursement limit:
                    <input className={s.amount_input_left} 
                        onChange={e => this.amountInputHandlerLeft(e, 'totalReimbursementLimit')}
                        value={this.amounts.totalReimbursementLimit.intPart}
                    />.
                    <input className={s.amount_input_right} 
                        onChange={e => this.amountInputHandlerRight(e, 'totalReimbursementLimit')}
                        value={this.amounts.totalReimbursementLimit.centPart}
                    />
                </label>    
                <button className={s.save_button} onClick={this.sendRequest}>Save changes</button>
                <Link className={s.back_link} to='/'>Back</Link>
            </div>
        )
    }
} export default withRouter(AdminPage);
