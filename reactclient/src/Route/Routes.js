import Login from "../Pages/Auth/Login";
import Register from "../Pages/Auth/Register";
import CreateProject from "../Pages/CreateProject/CreateProject";

export const authRoutes = [
    {
        path:"/create/project",
        Component: CreateProject
    }
]
export const publicRoutes = [
    {
        path:"/login",
        Component: Login
    }
]
export const notAuthRoutes = [
    {
        path:"/register",
        Component:Register
    }
]