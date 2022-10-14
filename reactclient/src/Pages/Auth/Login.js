import React, {Component} from 'react';
import "../../style/login.css"
import {authPost, mainUrl} from "../../forAxios/AxiosProp";

class Login extends Component {
    constructor(props) {
        super(props);
        this.auth = this.auth.bind(this)
        this.state = {
            email: "",
            password: ""
        }
    }
    auth() {
        console.log("click")
        authPost.post(mainUrl + "/login", {email:this.state.email,password:this.state.password},{headers:{
                'Content-Type': 'application/json'
            }})
            .then(res => {
                console.log(res)
            })
            .catch(er => {
                console.log(er)
            })

    }
    componentDidMount() {

    }

    render() {
        return (
            <div>
                <h2>Login page</h2>
                <div className="login-container">
                    <div className="login-wrap">
                        <input type="text" placeholder={"Email"}
                               onChange={event => this.setState({email:event.target.value})}/>
                        <input type="password" placeholder={"Password"}
                               onChange={event => this.setState({password:event.target.value})}/>
                        <button onClick={() => this.auth()}>Войти</button>
                    </div>
                </div>
            </div>
        );
    }
}

export default Login;