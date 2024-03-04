import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Provider } from "react-redux";
import { store } from "./redux/store";
import Dashboard from "./components/Dashboard";
import WatchlistPage from "./components/WatchlisPage";
import Stock from "./components/Stock";
import Portfolio from "./components/Portfolio";
const App: React.FC = () => {
  return (
    <Provider store={store}>
      <Router>
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/watchlistPage" element={<WatchlistPage />} />
          <Route path="/stock" element={<Stock />} />
          <Route path="/portfolio" element={<Portfolio />} />
        </Routes>
      </Router>
    </Provider>
  );
};

export default App;
