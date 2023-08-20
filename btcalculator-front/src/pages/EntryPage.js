import React from 'react';
import { server_url } from '../utils/config';
import { roles } from '../utils/roles';
import s from './EntryPage.module.css';
import { withRouter } from '../utils/withRouter';
import { Link } from 'react-router-dom';

class EntryPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
        }
    }

    componentDidMount() {}

    render() {
        return (
            <div className={s.page_container}>
                <div className={s.items_container}>
                    <h2 className={s.welcome_message}>Welcome to BTCalculator!</h2>
                    <h4 className={s.welcome_message__tagline}>
                        Calculate a reimbursement for your business trip fast and easy!</h4>
                    <p className={s.label}>Select your role:</p>
                    <div className={s.button_container}>
                        <Link className={s.role_button} to='/adminPage'>Admin</Link>
                        <Link className={s.role_button} to='/userPage'>User</Link>
                    </div>
                </div>
            </div>
        )
    }
} export default withRouter(EntryPage);