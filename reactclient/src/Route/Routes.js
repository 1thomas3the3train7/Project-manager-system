import Login from "../Pages/Auth/Login";
import Register from "../Pages/Auth/Register";

export const authRoutes = [
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