import React, { useEffect, useState } from 'react';
import axios from 'axios';

const ItemList = () => {
    const [items, setItems] = useState([]);
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const token = localStorage.getItem('token');
    useEffect(() => {
        const fetchItems = async () => {
            if (!token) {
                setError('User is not authenticated.');
                return;
            }
            try {
                const response = await axios.get(`http://localhost:8080/items/display`, {
                    headers: {
                        'Authorization': `Bearer ${token}`, // Add JWT token for authorization
                    },
                });
                setItems(response.data);
            } catch (err) {
                console.error("Error fetching items:", err);
                setError("Failed to fetch item details.");
            }
        };

        fetchItems();
    }, []);

    const handleWearItem = async (itemId) => {
        setError(null);
        setSuccess(null);
        if (!token) {
            setError('User is not authenticated.');
            return;
        }
        console.log(token);
        try {
            const response = await axios.post(`http://localhost:8080/items/wear/${itemId}`, {}, {
                headers: {
                    'Authorization': `Bearer ${token}`, // Add JWT token for authorization
                },
            });
            
            setSuccess(`Item worn successfully! Current wear count: ${response.data.wearCount}`);
            // Update the worn item in the local state
            setItems(prevItems =>
                prevItems.map(item => 
                    item.id === itemId ? { ...item, wearCount: response.data.wearCount } : item
                )
            );
        } catch (err) {
            console.error("Error wearing item:", err);
            setError("Failed to wear item. Please try again.");
        }
    };

    if (error) return <p style={{ color: 'red' }}>{error}</p>;
    if (items.length === 0) return <p>Loading...</p>; // Loading state while fetching

    return (
        <div className="item-list">
            <h1>Your Items</h1>
            {success && <p style={{ color: 'green' }}>{success}</p>}
            <ul>
                {items.map(item => (
                    <li key={item.id} className="item-detail">
                        <img src={item.imageUrl} alt={item.name} style={{ width: '100px', height: '100px' }} />
                        <h3>{item.name}</h3>
                        <p>Color: {item.color}</p>
                        <p>Category: {item.category}</p>
                        <p>Wear Count: {item.wearCount}</p>
                        <button onClick={() => handleWearItem(item.id)}>Wear Item</button>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ItemList;
