import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../styles/MyContacts.css'; // Import your CSS file

const MyContacts = () => {
    const [contacts, setContacts] = useState([]);
    const [error, setError] = useState('');
    const token = localStorage.getItem('token'); // Retrieve token

    useEffect(() => {
        const fetchContacts = async () => {
            // Check if token exists
            if (!token) {
                setError('User is not authenticated.');
                return;
            }

            try {
                console.log(token);
                const response = await axios.get('http://localhost:8080/api/contacts/mycontacts', {
                    headers: {
                        Authorization: `Bearer ${token}`, // Passing JWT for authentication
                        'Content-Type': 'application/json',
                    },
                });
                setContacts(response.data);
            } catch (err) {
                console.error('Error fetching contacts:', err);
                setError('Failed to fetch contacts.');
            }
        };

        fetchContacts();
    }, [token]); // Add token as a dependency to ensure it's watched

    // Handling the case when there's an error
    if (error) {
        return <div>{error}</div>;
    }

    // Render the contacts page
    return (
        <div className="my-contacts">
            <h2>People Who Contacted You</h2>
            {contacts.length === 0 ? (
                <p>No one has contacted you yet.</p>
            ) : (
                contacts.map(contact => (
                    <div key={contact.id} className="contact-card">
                        <h3>{contact.contactName}</h3>
                        <p>Email: {contact.contactEmail}</p>
                        <p>Message: {contact.message}</p>
                        <p>Regarding Item: {contact.clothingItem.title}</p>
                    </div>
                ))
            )}
        </div>
    );
};

export default MyContacts;
