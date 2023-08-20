import React from 'react';
import s from './Receipt.module.css';
import { withRouter } from '../utils/withRouter';

class Receipt extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return ( 
            <div className={s.receipt__container}>
                <label className={s.receipt__type_select__label}>
                    Type:
                    <select className={s.receipt__type_select} value={this.props.typeValue}
                        onChange={this.props.typeSelectHandler}>
                        { this.props.receiptTypes.map((elem, index) => 
                            <option key={index} value={elem.type}>{elem.type}</option>
                        )}
                    </select>
                </label>
                <br></br>
                <label className={s.receipt__amount_input__label}>
                    Amount:
                    <input className={s.receipt__amount_input} value={this.props.amountValue}
                        onChange={this.props.amountInputHandler}/>
                        <span>.</span>
                    <input className={s.receipt__cent_amount_input} value={this.props.centAmountValue}
                        onChange={this.props.centAmountInputHandler}/>
                    <span>$</span>
                </label>
                <button className={s.receipt__remove_button} onClick={this.props.removeHandler}>
                    Remove</button>
            </div>
        )
    }
} export default withRouter(Receipt);