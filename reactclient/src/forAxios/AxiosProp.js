import axios from "axios";

export const mainUrl = "http://localhost:8082";

export const authPost =axios.create({
    baseURL:mainUrl,
    withCredentials:true
})

const authInter = config => {
    config.headers.authorization = "Bearer " + sessionStorage.getItem("accessToken")
    return config;
}

authPost.interceptors.request.use(authInter);

function getAuthCont(url,data,header){
    function getAuthContFinal(){
        authPost.post(url,data,{withCredentials:true},header)
            .then(res => {
                console.log(res);
                return res;
            })
            .catch(er => {
                console.log(er)
            })
    }
    return authPost.post(url,data,{withCredentials:true},header)
        .then(res => {
            console.log(res)
            return res.data;
        })
        .catch(er => {
            console.log(er);
            return axios.post(mainUrl + "/getrefreshtoken",{},{withCredentials:true})
                .then(res => {
                    console.log(res);
                })
                .catch(er => {
                    console.log(er);
                })
        })
}