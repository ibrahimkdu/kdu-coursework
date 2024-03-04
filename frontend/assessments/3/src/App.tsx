import React, { useEffect } from "react";
import { useDispatch } from "react-redux";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './App.css'
import { GetRoom } from "./thunk/GetRoom";
import {Rooms} from "./components/Rooms";

function App() {
  const reduxDispatch = useDispatch();

  useEffect(() => {
    reduxDispatch(GetRoom());
  }, []);

  return (
    <>
       <BrowserRouter>
      <Routes>
        <Route path="/" element={<Rooms />} />
        {/* <Route path="/product/:id" element={<ProductDetail />} /> */}
      </Routes>
    </BrowserRouter>
    </>
  )
}

export default App
