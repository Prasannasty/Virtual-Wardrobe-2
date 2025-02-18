import React, { useEffect, useState } from 'react';
import axios from 'axios';
 import ClothingItemCard from './ClothingItemCard';
import '../styles/CommunityPage.css'

const CommunityPage = () => {
    const [items, setItems] = useState([]);
    const [error, setError] = useState('');
    const token = localStorage.getItem('token'); // Retrieve token

    useEffect(() => {
        const fetchItems = async () => {
            // Check if token exists
            if (!token) {
                setError('User is not authenticated.');
                return;
            }

            try {
                const response = await axios.get('http://localhost:8080/api/clothing/all', {
                    headers: {
                        Authorization: `Bearer ${token}`,
                        'Content-Type': 'application/json',
                    },
                });
                setItems(response.data);
            } catch (err) {
                console.error('Error fetching items:', err);
                setError('Failed to fetch items.');
            }
        };

        fetchItems();
    }, [token]); // Add token as a dependency

    // Handling the case when there's an error
    if (error) {
        return <div>{error}</div>;
    }

    // Render the community page
    return (
        <div className="community-page">
            <h2>Community Exchange</h2>
            <div className="item-list">
                {items.length > 0 ? (
                    items.map(item => (
                        <ClothingItemCard key={item.id} item={item} />
                    ))
                ) : (
                    <p>No items available.</p>
                )}
            </div>
        </div>
    );
};

export default CommunityPage;
