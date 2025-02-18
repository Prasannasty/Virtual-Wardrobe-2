import React from 'react'
import logo from "../assets/logo.png";
import "../styles/Navbar.css";

export default function Navbar() {
  return (
    <>
      <nav className="navbar">
            <div className="navbar-left">
              <img src={logo} alt="Virtual Wardrobe Logo" className="app-logo" />
              <h2>Virtual Wardrobe</h2>
            </div>
            <ul className="navbar-right">
              <li>
                <a href="#features">Community Page</a>
              </li>
              <li>
                <a href="#about">Recommendation</a>
              </li>
              <li>
                <a href="#contact">Contact</a>
              </li>
            </ul>
          </nav>
    
    
    </>
  )
}
