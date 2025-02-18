import React from 'react';
import { useNavigate } from 'react-router-dom';
import "../styles/Home.css";
import Navbar from "./Navbar";

const Home = () => {
  const navigate = useNavigate();

  const handleCardClick = (route) => {
    navigate(route); // Navigate to the specific route
  };

  return (
    <>
      <Navbar />
      <div className="hero-container">
        <div className="hero-content">
          <div className="hero-text">
            <h1>Revolutionize Your Style with Virtual Wardrobe</h1>
            <p>
              Discover the future of fashion! AI Clothes Changer lets you
              explore endless outfit possibilities in seconds. From casual wear
              to elegant evening attire, our platform empowers you to create
              your dream wardrobe with ease.
            </p>
            <p>
              Experience the magic of AI as it transforms your wardrobe, helps
              you plan your outfits, and provides recommendations tailored just
              for you. Step into a sustainable and stylish future today.
            </p>
            <a href="/itemadd" className="cta-button">
              Get Started
            </a>
          </div>
          <div className="hero-image">
            <img src="/wardrobe.png" alt="Virtual Wardrobe" />
          </div>
        </div>
      </div>

      <div className="features-section">
        <h2 className="features-heading">Key Features of Virtual Wardrobe</h2>
        <div className="features-cards">
          <div
            className="item-card"
            onClick={() => handleCardClick('/postdisplay')}
            style={{ cursor: 'pointer' }}
          >
            <div className="image-box">
              <img src="/outfit-planner.png" alt="Outfit Planner" />
            </div>
            <div className="content">
              <h3>Community Page</h3>
              <p>Share and explore outfit ideas with friends and community.</p>
            </div>
          </div>

          <div
            className="item-card"
            onClick={() => handleCardClick('/itemadd')}
            style={{ cursor: 'pointer' }}
          >
            <div className="image-box">
              <img src="/usage-tracker.png" alt="Usage Tracker" />
            </div>
            <div className="content">
              <h3>Item Upload</h3>
              <p>
                Easily upload items to your wardrobe by adding photos and key
                details such as name, category, and usage.
              </p>
            </div>
          </div>

          <div
            className="item-card"
            onClick={() => handleCardClick('/itemdisplay')}
            style={{ cursor: 'pointer' }}
          >
            <div className="image-box">
              <img src="/ai-recommendations.png" alt="AI Recommendations" />
            </div>
            <div className="content">
              <h3>Item List</h3>
              <p>
                View your entire wardrobe at a glance with our organized and
                visually appealing item list.
              </p>
            </div>
          </div>

          <div
            className="item-card"
            onClick={() => handleCardClick('/recommend')}
            style={{ cursor: 'pointer' }}
          >
            <div className="image-box">
              <img src="/sustainability-tracker.png" alt="Sustainability Tracker" />
            </div>
            <div className="content">
              <h3>Recommendation Component</h3>
              <p>
                Get personalized outfit recommendations based on your wardrobe
                and preferences.
              </p>
            </div>
          </div>

          <div
            className="item-card"
            onClick={() => handleCardClick('/postadd')}
            style={{ cursor: 'pointer' }}
          >
            <div className="image-box">
              <img src="/wardrobe-organizer.png" alt="Wardrobe Organizer" />
            </div>
            <div className="content">
              <h3>Add ClothingPost</h3>
              <p>
                Easily post details of your clothing items, including images,
                category, and description.
              </p>
            </div>
          </div>

          <div
            className="item-card"
            onClick={() => handleCardClick('/track')}
            style={{ cursor: 'pointer' }}
          >
            <div className="image-box">
              <img src="/Track Usage.png" alt="Track Usage" />
            </div>
            <div className="content">
              <h3>Track Usage</h3>
              <p>
                Track clothing usage effortlessly to make informed decisions
                about your wardrobe.
              </p>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Home;
