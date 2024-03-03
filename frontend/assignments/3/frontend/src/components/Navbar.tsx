import { Link } from 'react-router-dom';

const Navbar: React.FC = () => {
  const navbarStyle: React.CSSProperties = {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: "1rem",
    borderBottom: "1px solid black",
  };

  const linkStyle: React.CSSProperties = {
    textDecoration: "none",
    color: "#000",
    margin: "0 1rem",
    cursor: "pointer",
  };

  return (
    <div style={navbarStyle}>
      <h2>Stock App</h2>
      <div>
        <Link to="/" style={linkStyle}>Explore</Link>
        <Link to="/watchlistPage" style={linkStyle}>Watchlist</Link>
      </div>
    </div>
  );
};

export default Navbar;
