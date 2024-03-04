import React, { useState, useEffect } from 'react';
import { useSelector } from 'react-redux';
import { RootState } from '../redux/store';
import io from "socket.io-client";
import NavbarStock from './NavbarStock';

function Stock() {
  const watchlist = useSelector((state: RootState) => state.stocks.stocks);
  const [price, setPrice] = useState<number>(100);
  const [prevPrice, setPrevPrice] = useState<number | null>(null);
  const [quantity, setQuantity] = useState<number>(0);
  const [history, setHistory] = useState<{ price: number, timestamp: string, action: string, quantity: number }[]>([]);

  useEffect(() => {
    const socket = io("http://localhost:3000"); 

    socket.on("new-price", (newPrice: number) => {
      setPrevPrice(price); 
      setPrice(newPrice); 
    });

    return () => {
      socket.disconnect(); 
    };
  }, [price]);

  const handleBuy = () => {
    const newTransaction = { price, timestamp: new Date().toLocaleString(), action: 'BUY', quantity };
    setHistory(prevHistory => [...prevHistory, newTransaction]);
  };

  const handleSell = () => {
    const newTransaction = { price, timestamp: new Date().toLocaleString(), action: 'SELL', quantity };
    setHistory(prevHistory => [...prevHistory, newTransaction]);
  };

  return (
    <div>
    <NavbarStock />
      <div className="container" style={{ display: 'flex', flexDirection: 'row' }}>
        <div className="main" style={{ display: 'flex', flexDirection: 'column', flexBasis: '75%' }}>
          <div className="content" style={{ display: 'flex', flexDirection: 'row', justifyContent: 'space-between' }}>
            <div className="items" style={{ display: 'flex', flexDirection: 'row', flexGrow: 1, justifyContent: 'space-around', border: '1px solid black', margin: '0.4em' }}>
              <div className="log">
                <select>
                  {watchlist.map(stock => (
                    <option key={stock.stock_symbol} value={stock.stock_symbol}>{stock.stock_name}</option>
                  ))}
                </select>
              </div>
            </div>
            <div className="items" style={{ display: 'flex', flexDirection: 'row', flexGrow: 1, justifyContent: 'space-around' }}>
              <div className="priceHeader" id="priceChange">Price</div>
              <div id="price">
                <span style={{ color: prevPrice !== null && price > prevPrice ? 'green' : 'red' }}>{price}</span>
              </div>
              <div className="arrow">{prevPrice !== null && price > prevPrice ? '▲' : '▼'}</div>
              <div className="percentage">{prevPrice !== null ? `${((price - prevPrice) / prevPrice * 100).toFixed(2)} %` : ''}</div>
            </div>
            <div className="items" style={{ display: 'flex', flexDirection: 'row', flexGrow: 1, justifyContent: 'space-around' }}>
              <div className="quantity">
                <span>Enter quantity</span>
                <input type="number" value={quantity} onChange={(e) => setQuantity(Number(e.target.value))} />
              </div>
            </div>
            <div className="buy" style={{ display: 'flex', flexDirection: 'row', flexGrow: 1, justifyContent: 'space-around', border: '1px solid #2f9e44', color: '#2f9e44', backgroundColor: '#b2f2bb', margin: '0.4em', width: '0.1%' }} onClick={handleBuy}>
              <span>BUY</span>
            </div>
            <div className="sell" style={{ display: 'flex', flexDirection: 'row', flexGrow: 1, justifyContent: 'space-around', border: '1px solid #e03131', color: '#e03131', backgroundColor: '#ffc9c9', margin: '0.4em', width: '0.1%' }} onClick={handleSell}>
              <span>SELL</span>
            </div>
          </div>
        </div>
        <div className="side" style={{ display: 'flex', flexBasis: '25%', flexDirection: 'column' }}>
          <div className="historyheading" style={{ width: '100%', display: 'flex', justifyContent: 'left' }}>History</div>
          <div className="history" style={{ display: 'flex', flexDirection: 'column', border: '1px solid black', borderRadius: '10px', padding: '8px', overflowY: 'auto' }}>
            {history.map((item, index) => (
              <div key={index} className="historydata" style={{ display: 'flex', flexDirection: 'row', border: '1px solid black', borderRadius: '10px', padding: '8px', marginBottom: '5px' }}>
                <div className="historycontent" style={{ display: 'flex', flexDirection: 'column', gap: '1vh', flexBasis: '80%' }}>
                  <div className="historyprice">
                    <span>{item.price}</span>
                    <span>Stocks</span>
                  </div>
                  <div className="historyTime">{item.timestamp}</div>
                </div>
                <div className="historyStatus" style={{ display: 'flex', flexBasis: '20%', justifyContent: 'center', alignItems: 'center' }}>
                  <span>{item.action}</span>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}

export default Stock;

