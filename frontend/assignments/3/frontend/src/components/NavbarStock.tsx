import React from "react";
import Logo from "./stockimg.png";

function NavbarStock() {
  return (
    <div>
      <div style={{ height: "3vh", width: "100%", display: "flex", flexDirection: "row", color: "white", backgroundColor: "#1971c2" }}>
        <div style={{ height: "3vh" }}>
          <img src={Logo} alt="" style={{ height: "3vh", marginLeft: "0.25em" }} />
        </div>
        <div style={{ marginLeft: "1em", color: "white", height: "3vh", marginTop: "0.5em" }}>KDU STOCK MARKET</div>
        <div style={{ marginLeft: "70em", color: "white", marginTop: "0.5em" }}>
          <a href="/summariser" style={{ textDecoration: "none", color: "white", height: "3vh" }}>
            <span>SUMMARIZER</span>
          </a>
        </div>
        <div style={{ marginLeft: "5em", color: "white", marginTop: "0.5em" }}>
          <a href="/portfolio" style={{ textDecoration: "none", color: "white", height: "3vh" }}>
            <span>My portfolio</span>
          </a>
        </div>
      </div>
    </div>
  );
}

export default NavbarStock;
