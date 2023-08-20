import React from 'react';
import s from './ReceiptType.module.css';
import { withRouter } from '../utils/withRouter';

class ReceiptType extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return ( 
            <div className={s.receipt__container}>
                <label className={s.receipt__name_input_label}>
                    Name:
                    <input className={s.receipt__name_input} value={this.props.name} 
                        onChange={this.props.nameInputHandler}/>
                </label>
                <br></br>
                <label className={s.receipt__limit_input_label}>
                    Limit:
                    <input className={s.receipt__limit_input_left} value={this.props.limit.intPart}
                        onChange={this.props.limitInputHandlerLeft}/>
                        <span>.</span>
                    <input className={s.receipt__limit_input_right} value={this.props.limit.centPart}
                        onChange={this.props.limitInputHandlerRight}/>
                    <span>$</span>
                </label>
                <button className={s.receipt__remove_button} onClick={this.props.removeHandler}>
                    Remove</button>
            </div>
        )
    }
} export default withRouter(ReceiptType);