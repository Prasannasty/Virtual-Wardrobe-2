import React, { useState } from 'react';
import axios from 'axios';
import '../styles/RecommendationComponent.css'; // Import the CSS file

const RecommendationComponent = () => {
    const [recommendations, setRecommendations] = useState([]);
    const [selection, setSelection] = useState('');
    const [inputValue, setInputValue] = useState('');
    const [error, setError] = useState('');
    const token = localStorage.getItem('token');

    if (!token) {
        setError('User is not authenticated.');
        return null;
    }

    const fetchRecommendations = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/recommendations/${selection}`, {
                params: { [selection === 'weather' ? 'weather' : 'occasion']: inputValue },
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            setRecommendations(response.data);
        } catch (error) {
            console.error('Error fetching recommendations', error);
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        fetchRecommendations();
    };

    return (
        <div className="recommendation-container">
            <h2>Get Clothing Recommendations</h2>
            <form onSubmit={handleSubmit} className="recommendation-form">
                <select onChange={(e) => setSelection(e.target.value)} required className="input-select">
                    <option value="">Select Recommendation Type</option>
                    <option value="weather">Weather-Based</option>
                    <option value="event">Event-Based</option>
                </select>
                <input
                    type="text"
                    placeholder={selection === 'weather' ? 'Enter weather (summer/rainy/winter)' : 'Enter occasion'}
                    value={inputValue}
                    onChange={(e) => setInputValue(e.target.value)}
                    required
                    className="input-text"
                />
                <button type="submit" className="submit-button">Get Recommendations</button>
            </form>
            <div className="recommendation-list">
                <h3>Recommendations:</h3>
                <div className="cards">
                    {recommendations.map(item => (
                        <div className="card" key={item.id}>
                            <img src={item.imageUrl} alt={item.name} className="card-image" />
                            <div className="card-content">
                                <h4>{item.name}</h4>
                                <p>Category: {item.category}</p>
                                <p>Occasion: {item.occasion}</p>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default RecommendationComponent;
