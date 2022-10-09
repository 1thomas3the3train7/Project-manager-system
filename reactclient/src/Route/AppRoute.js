import React from 'react';
import {Route, Routes} from "react-router-dom"
import {authRoutes, notAuthRoutes,publicRoutes} from "./Routes";

function AppRoute(props) {
    return (
        <div>
            <Routes>
                {notAuthRoutes.map(({path,Component}) =>
                    <Route key={path} path={path} element={<Component/>}/>)}

                {publicRoutes.map(({path,Component}) =>
                    <Route key={path} path={path} element={<Component/>}/>)}

                {authRoutes.map(({path,Component}) =>
                    <Route key={path} path={path} element={<Component/>}/>)}
            </Routes>
        </div>
    );
}

export default AppRoute;