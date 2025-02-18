import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Welcome.css';

const Welcome = () => {
  const navigate = useNavigate(); // Initialize navigate function

  const handleGetStartedClick = () => {
    navigate('/login'); // Navigate to the login page
  };

  return (
    <>
    <h1 className="project-title">OUTFITPAL</h1>
     
      <div className="features-section">
        {/* Featured Items Section */}
        <section className="featured-items">
          <div className="item-grid">
            <div className="item-card">
              <div className="image-box">
                <img src="wardrobe.png" alt="Casual Shirt"/>
              </div>
              <div className="content">
                <h3>Casual Shirt</h3>
                <p>Shirts</p>
              </div>
            </div>
            <div className="item-card">
              <div className="image-box">
                <img src="wardrobe.png" alt="Casual Shirt"/>
              </div>
              <div className="content">
                <h3>Casual Shirt</h3>
                <p>Shirts</p>
              </div>
            </div>
            <div className="item-card">
              <div className="image-box">
                <img src="wardrobe.png" alt="Casual Shirt"/>
              </div>
              <div className="content">
                <h3>Casual Shirt</h3>
                <p>Shirts</p>
              </div>
            </div>

            <button onClick={handleGetStartedClick}>Get Started</button>


            <div className="item-card">
              <div className="image-box">
                <img src="wardrobe.png" alt="Casual Shirt"/>
              </div>
              <div className="content">
                <h3>Add  Items</h3>
                <p>Easily add clothing items to your wardrobe by uploading pictures and entering details.</p>
              </div>
            </div>
            <div className="item-card">
              <div className="image-box">
                <img src="wardrobe.png" alt="Summer Dress"/>
              </div>
              <div className="content">
                <h3>Plan Outfits</h3>
                <p>Use our outfit planner to mix and match items, ensuring you look your best every day.</p>
              </div>
            </div>
            <div className="item-card">
              <div className="image-box">
                <img src="wardrobe.png" alt="Running Shoes"/>
              </div>
              <div className="content">
                <h3> Track Usage</h3>
                <p>Monitor how often you wear each item and receive suggestions on decluttering.</p>
              </div>
            </div>
          </div>
        </section>
        </div>
       

        {/* How It Works Section */}
        {/* <div className="features-section"> */}
        {/* <section className="how-it-works">
          <div className="steps-container">
            <div className="step-card">
              <div className="icon">🛒</div>
              <h3>Add Items</h3>
              <p>Easily add clothing items to your wardrobe by uploading pictures and entering details.</p>
            </div>
            <div className="step-card">
              <div className="icon">💬</div>
              <h3>Plan Outfits</h3>
              <p>Use our outfit planner to mix and match items, ensuring you look your best every day.</p>
            </div>
            <div className="step-card">
              <div className="icon">📊</div>
              <h3>Track Usage</h3>
              <p>Monitor how often you wear each item and receive suggestions on decluttering.</p>
            </div>
          </div>
        </section> */}
      {/* </div> */}
    </>
  );
};

export default Welcome;
