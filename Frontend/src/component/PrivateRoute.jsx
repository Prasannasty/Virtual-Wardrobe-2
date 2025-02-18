import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ children }) => {
    const token = localStorage.getItem('token'); // Get JWT from local storage
    console.log("Token from localStorage:", token);
    // Check if the token exists; if not, redirect to login
    return token ? children : <Navigate to="/" />;
};

export default PrivateRoute;
