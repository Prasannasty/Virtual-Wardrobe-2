import React, { useState } from "react";
import axios from "axios";
import "../styles/ItemUpload.css"; // Import the CSS file

const ItemUpload = () => {
  const [image, setImage] = useState(null);
  const [imagePreview, setImagePreview] = useState(null);
  const [name, setName] = useState("");
  const [category, setCategory] = useState("");
  const [color, setColor] = useState("");
  const [occasion, setOccasion] = useState("");
  const [wearCount, setWearCount] = useState(0);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    setImage(file);
    setImagePreview(URL.createObjectURL(file));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const token = localStorage.getItem("token");

    if (!token) {
      setError("User is not authenticated.");
      return;
    }

    const formData = new FormData();
    formData.append("image", image);
    formData.append("name", name);
    formData.append("category", category);
    formData.append("color", color);
    formData.append("occasion", occasion);
    formData.append("wearCount", wearCount);

    try {
      const response = await axios.post("http://localhost:8080/items/add", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          Authorization: `Bearer ${token}`,
        },
      });
      setSuccessMessage("Item uploaded successfully!");
      setError("");
      // Reset form
      setName("");
      setCategory("");
      setColor("");
      setOccasion("");
      setWearCount(0);
      setImage(null);
      setImagePreview(null);
    } catch (error) {
      console.error("Error uploading item:", error);
      setError("Failed to upload item. Please try again.");
    }
  };

  return (
    <div className="container">
      <h2>Upload Your Item</h2>
      <form onSubmit={handleSubmit}>
        {error && <div className="error">{error}</div>}
        {successMessage && <div className="success">{successMessage}</div>}
        <div className="form-group">
          <label>Item Name:</label>
          <input
            type="text"
            placeholder="Enter item name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Category:</label>
          <select value={category} onChange={(e) => setCategory(e.target.value)} required>
            <option value="">Select a Category</option>
            <option value="Summer Wear">Summer Wear</option>
            <option value="Winter Wear">Winter Wear</option>
            <option value="Rainy Wear">Rainy Wear</option>
          </select>
        </div>
        <div className="form-group">
          <label>Color:</label>
          <input
            type="text"
            placeholder="Enter color"
            value={color}
            onChange={(e) => setColor(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Occasion:</label>
          <input
            type="text"
            placeholder="E.g., Party, Casual, Formal"
            value={occasion}
            onChange={(e) => setOccasion(e.target.value)}
            required
          />
        </div>
        <div className="form-group">
          <label>Wear Count:</label>
          <input
            type="number"
            value={wearCount}
            onChange={(e) => setWearCount(e.target.value)}
            min="0"
            required
          />
        </div>
        <div className="form-group">
          <label>Image:</label>
          <input type="file" onChange={handleFileChange} required />
          {imagePreview && <img src={imagePreview} alt="Preview" className="image-preview" />}
        </div>
        <button type="submit">Upload Item</button>
      </form>
    </div>
  );
};

export default ItemUpload;
