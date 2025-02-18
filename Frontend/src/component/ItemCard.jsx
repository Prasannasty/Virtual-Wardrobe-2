import React from 'react';
import '../styles/ItemCard.css'; // Import the CSS file for styling

const ItemCard = ({ item }) => {
  return (
    <div className="item-card">
      <img src={item.imageUrl} alt={item.name} className="item-image" />
      <div className="item-details">
        <h3>{item.name}</h3>
        <p><strong>Category:</strong> {item.category}</p>
        <p><strong>Color:</strong> {item.color}</p>
        <p><strong>Occasion:</strong> {item.occasion}</p>
        <p><strong>Wear Count:</strong> {item.wearCount}</p>
      </div>
    </div>
  );
};

export default ItemCard;
