import React, {Component} from 'react';
import axios from "axios";
import {mainUrl, mainUrl1} from "../../forAxios/AxiosProp";
import "../../style/register.css"

class Register extends Component {
    constructor(props) {
        super(props);
        this.register = this.register.bind(this);
        this.state = {
            email:"",
            password:"",
            firstName:""
        }
    }
    register(){
        axios.post(mainUrl + "/register",{
            email:this.state.email,
            password:this.state.password,
            firstName:this.state.name},{headers:{
                'Content-Type': 'application/json'
            },withCredentials:true})
            .then(res => console.log(res))
    }
    render() {
        return (
            <div>
                <h2>Register page</h2>
                <div className="reg-container">
                    <div className="reg-wrap">
                        <input type="text" placeholder={"Email"}
                               onChange={event => this.setState({email:event.target.value})}/>
                        <input type="password" placeholder={"Password"}
                               onChange={event => this.setState({password:event.target.value})}/>
                        <input type="text" placeholder={"Name"}
                               onChange={event => this.setState({name:event.target.value})}/>
                        <button onClick={() => this.register()}>Зарегистрироваться</button>
                    </div>
                </div>
            </div>
        );
    }
}

export default Register;