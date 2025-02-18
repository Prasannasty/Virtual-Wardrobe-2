import React, { useEffect, useState } from 'react';
import axios from 'axios';
import ItemCard from './ItemCard'; // Import the ItemCard component
import '../styles/ItemList.css'
const ItemList = () => {
  const [items, setItems] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchItems = async () => {
        const token = localStorage.getItem('token');
      if (!token) {
        setError('User is not authenticated.');
        return;
      }
      try {
        const response = await axios.get('http://localhost:8080/items/display', {
            headers: {
              'Authorization': `Bearer ${token}`,
            },
          }) // Adjust URL as needed
        setItems(response.data);
      } catch (err) {
        setError('Failed to fetch items');
        console.error(err);
      }
    };

    fetchItems();
  }, []);

  return (
    <div className="item-list">
      {error && <p>{error}</p>}
      <div className="item-grid">
        {items.map((item) => (
          <ItemCard key={item.id} item={item} />
        ))}
      </div>
    </div>
  );
};

export default ItemList;
