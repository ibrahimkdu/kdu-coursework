import React from 'react';
import { Link } from 'react-router-dom';

const Navbar: React.FC = () => {
  const navbarStyle: React.CSSProperties = {
    display: "flex",
    justifyContent: "flex-start",
    alignItems: "center",
    padding: "1rem",
    borderBottom: "1px solid black",
  };

  const linkContainerStyle: React.CSSProperties = {
    marginLeft: "1rem",
  };

  const linkStyle: React.CSSProperties = {
    textDecoration: "none",
    color: "#000",
    margin: "0 1rem",
    cursor: "pointer",
  };

  return (
    <div style={navbarStyle}>
      <div style={linkContainerStyle}>
        <Link to="/" style={linkStyle}>Explore</Link>
        <Link to="/watchlistPage" style={linkStyle}>My Watchlist</Link>
      </div>
    </div>
  );
};

export default Navbar;
