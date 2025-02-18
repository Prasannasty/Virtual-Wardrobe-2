import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import ApiService from "./ApiService";
import '../styles/LoginPage.css'; // Import the CSS file
import '../styles/googlebtn.css';
import '../styles/githubbtn.css';

function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const location = useLocation();

    // Set the default redirect path to '/home'
    const from = location.state?.from?.pathname || '/home';

    // Function to handle Google Login Success
    const handleGoogleLogin = async () => {
        try {
            const response = await fetch('http://localhost:8080/auth/google/success', {
                method: 'GET',
                credentials: 'include',
            });
            const userInfo = await response.json();
            console.log(userInfo);
            // Save user info or token in localStorage
            localStorage.setItem('user', JSON.stringify(userInfo));
            navigate('/home'); // Redirect to home page
        } catch (error) {
            console.error("Google Login failed:", error);
            setError("Google Login failed. Please try again.");
            setTimeout(() => setError(''), 5000);
        }
    };

    // Automatically trigger Google login handling if redirected
    useEffect(() => {
        if (location.pathname === '/auth/google/success') {
            handleGoogleLogin();
        }
    }, [location.pathname]);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!email || !password) {
            setError('Please fill in all fields.');
            setTimeout(() => setError(''), 5000);
            return;
        }

        try {
            const response = await ApiService.loginUser({ email, password });
            if (response.statuscode === 200) {
                console.log(response);
                localStorage.setItem('token', response.token);
                localStorage.setItem('role', response.role);
                // Redirect to the home page after successful login
                navigate('/home', { replace: true });
            }
        } catch (error) {
            setError(error.response?.data?.message || error.message);
            setTimeout(() => setError(''), 5000);
        }
    };

    return (
        <div className="auth-container">
            <h2>Login</h2>
            {error && <p className="error-message">{error}</p>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Login</button>
            </form>

            <p className="register-link">
                Don't have an account? <a href="/register">Register</a>
            </p>

            <div className="login-with-google-btn">
                <a className="btn btn-danger" href="http://localhost:8080/oauth2/authorization/google">
                    Login with Google
                </a>
            </div>


            <div className="login-with-github-btn">
                <a className="btn btn-danger" href="http://localhost:8080/oauth2/authorization/github">
                    Login with Github
                </a>
            </div>


        </div>
    );
}

export default LoginPage;
