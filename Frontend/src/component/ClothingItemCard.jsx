import React, { useState } from 'react';
import axios from 'axios';
import '../styles/ClothingItemCard.css'
const ClothingItemCard = ({ item }) => {
    const [showContactForm, setShowContactForm] = useState(false);
    const [contactDetails, setContactDetails] = useState({
        contactName: '',
        contactEmail: '',
        message: '',
    });
    const token = localStorage.getItem('token');

    // Early return if user is not authenticated
    if (!token) {
        return <p>User is not authenticated.</p>;
    }

    const handleContactChange = (e) => {
        const { name, value } = e.target;
        setContactDetails({ ...contactDetails, [name]: value });
    };

    const handleContactSubmit = async (e) => {
        e.preventDefault(); // Prevent form submission from refreshing the page

        try {
            await axios.post('http://localhost:8080/api/contacts/send',
                {
                    ...contactDetails,
                    clothingItemId: item.id,
                    uploaderId: item.user.id, // Assuming item.user has the uploader's ID
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`, // Passing JWT for authentication
                        "Content-Type": "application/json",
                    },
                }
            );

            alert('Contact details sent!');
            setShowContactForm(false);
        } catch (error) {
            console.error('Error sending contact details:', error);
            alert('Failed to send contact details. Please try again.'); // Improved error handling
        }
    };
    const formattedDate = new Date(item.date).toLocaleDateString();
    return (
        <div className="clothing-item-card">
            <h3>{item.title}</h3>
            <img src={item.imageUrl} alt={item.title} />
            <p><strong>Description:</strong> {item.description}</p>
            <p><strong>Category:</strong> {item.category}</p>
            <p><strong>Size:</strong> {item.size}</p>
            <p><strong>Condition:</strong> {item.clothcondition}</p>
            <p><strong>Price:</strong> {item.price ? `$${item.price}` : 'Not specified'}</p>
            <p><strong>Material:</strong> {item.material}</p>
            <p><strong>Location:</strong> {item.location}</p>
            <p><strong>Date Posted:</strong> {item.createddate}</p>
            <button onClick={() => setShowContactForm(!showContactForm)}>
                Express Interest
            </button>

            {showContactForm && (
                <div className="contact-form">
                    <input
                        type="text"
                        name="contactName"
                        placeholder="Your Name"
                        value={contactDetails.contactName}
                        onChange={handleContactChange}
                    />
                    <input
                        type="email"
                        name="contactEmail"
                        placeholder="Your Email"
                        value={contactDetails.contactEmail}
                        onChange={handleContactChange}
                    />
                    <textarea
                        name="message"
                        placeholder="Message"
                        value={contactDetails.message}
                        onChange={handleContactChange}
                    ></textarea>
                    <button onClick={handleContactSubmit}>Send</button>
                </div>
            )}
        </div>
    );
};

export default ClothingItemCard;
