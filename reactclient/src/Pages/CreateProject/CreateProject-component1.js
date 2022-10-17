import React, {useState} from 'react';
import axios from "axios";
import {authPost, mainUrl, mainUrl1} from "../../forAxios/AxiosProp";

function CreateProjectComponent1(props) {
    const [name,setName] = useState("");
    function searchByName(name){
        authPost.post(mainUrl + "/search/user",{name:name})
            .then(res => console.log(res.data))
    }
    return (
        <div className={"createproject-wrap"}>
            <div className={"project-title"}>
                <p>Создание проекта</p>
            </div>
            <div className="projects-fields">
                <div className="fields-name">
                    <input type="text" placeholder={"Название"}/>
                </div>
                <div className="fields-passport">
                    <input type="text" placeholder={"Дата начала"}/>
                    <input type="text" placeholder={"Дата конца"}/>
                    <input type="text" placeholder={"Заказчик"}/>
                    <input type="text" placeholder={"Описание"}/>
                </div>
                <div className="fields-budget">
                    <input type="text" placeholder={"Бюджет"}/>
                    <input type="text" placeholder={""}/>
                </div>
                <div className="fields-cost">
                    <input type="text" placeholder={"Затраты"}/>
                </div>
                <div className="fields-user">
                    {/*<input type="text" placeholder={"Участники"}/>*/}
                    <p>Поиск по имени</p>
                    <input type="text" placeholder={"Введите имя"} onChange={event => searchByName(event.target.value)}/>
                    <div className="fiels-search-res">

                    </div>
                </div>
            </div>
        </div>
    );
}

export default CreateProjectComponent1;