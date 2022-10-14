import React, {Component} from 'react';
import CreateProjectComponent1 from "./CreateProject-component1";
import "../../style/createproject.css"

class CreateProject extends Component {
    render() {
        return (
            <div className={"container"}>
                <CreateProjectComponent1/>
            </div>
        );
    }
}

export default CreateProject;