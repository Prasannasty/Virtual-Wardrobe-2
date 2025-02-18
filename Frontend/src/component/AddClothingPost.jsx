import React, { useState } from 'react';
import axios from 'axios';
import '../styles/AddClothingPost.css'; // Import your CSS file

const AddClothingPost = () => {
    const [postDetails, setPostDetails] = useState({
        title: '',
        description: '',
        category: 'sell',
        size: '',
        condition: '',
        price: '',
        material: '',
        location: '',
        image: null,
    });
    const [error, setError] = useState('');
    const token = localStorage.getItem('token');
    
    if (!token) {
        setError('User is not authenticated.');
        return null;
    }

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setPostDetails({
            ...postDetails,
            [name]: value,
        });
    };

    const handleImageChange = (e) => {
        setPostDetails({
            ...postDetails,
            image: e.target.files[0],
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('title', postDetails.title);
        formData.append('description', postDetails.description);
        formData.append('category', postDetails.category);
        formData.append('size', postDetails.size);
        formData.append('condition', postDetails.condition);
        formData.append('price', postDetails.price);
        formData.append('material', postDetails.material);
        formData.append('location', postDetails.location);
        formData.append('image', postDetails.image);

        try {
            await axios.post('http://localhost:8080/api/clothing/add', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    Authorization: `Bearer ${token}`,
                },
            });

            alert('Clothing item added successfully!');
            setPostDetails({
                title: '',
                description: '',
                category: 'sell',
                size: '',
                condition: '',
                price: '',
                material: '',
                location: '',
                image: null,
            });
        } catch (error) {
            console.error('Error adding clothing item:', error);
            alert('Failed to add item. Please try again.');
        }
    };

    return (
        <div className="add-clothing-post-container">
            <h2>Add New Clothing Item</h2>
            <form onSubmit={handleSubmit} encType="multipart/form-data">
                <div>
                    <label>Title:</label>
                    <input
                        type="text"
                        name="title"
                        value={postDetails.title}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div>
                    <label>Description:</label>
                    <textarea
                        name="description"
                        value={postDetails.description}
                        onChange={handleInputChange}
                        required
                    ></textarea>
                </div>
                <div>
                    <label>Category:</label>
                    <select name="category" value={postDetails.category} onChange={handleInputChange}>
                        <option value="sell">Sell</option>
                        <option value="lend">Lend</option>
                        <option value="donate">Donate</option>
                    </select>
                </div>
                <div>
                    <label>Size:</label>
                    <input
                        type="text"
                        name="size"
                        value={postDetails.size}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div>
                    <label>Condition:</label>
                    <input
                        type="text"
                        name="condition"
                        value={postDetails.condition}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div>
                    <label>Price:</label>
                    <input
                        type="number"
                        name="price"
                        value={postDetails.price}
                        onChange={handleInputChange}
                        placeholder="Optional"
                    />
                </div>
                <div>
                    <label>Material:</label>
                    <input
                        type="text"
                        name="material"
                        value={postDetails.material}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div>
                    <label>Location:</label>
                    <input
                        type="text"
                        name="location"
                        value={postDetails.location}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div>
                    <label>Upload Image:</label>
                    <input type="file" name="image" onChange={handleImageChange} required />
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    );
};

export default AddClothingPost;
