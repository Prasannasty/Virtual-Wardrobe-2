import axios from 'axios';

// Define the base URL for your Spring Boot API
const API_BASE_URL = 'http://localhost:8080'; // Adjust based on your Spring Boot setup

// Create the axios instance with defaults
const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  }
});

// Add loginUser method
const loginUser = async (credentials) => {
  try {
    const response = await api.post('/auth/login', credentials); // Assuming your Spring Boot endpoint is /api/auth/login
    return response.data;
  } catch (error) {
    throw error;
  }
};

// Add registerUser method
const registerUser = async (userData) => {
  try {
    const response = await api.post('/auth/register', userData); // Assuming your Spring Boot endpoint is /api/auth/register
    return response.data;
  } catch (error) {
    throw error;
  }
};

export default {
  loginUser,
  registerUser
};
