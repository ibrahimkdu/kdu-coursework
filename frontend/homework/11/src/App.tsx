import React, { useEffect, useState } from "react";
import "./App.scss";
import { ApiQuote } from "./types/quotes.types";
import { Quote } from "./Quote";

function App() {
  const [allQuotes, setAllQuotes] = useState<ApiQuote[]>([]);
  const [quotes, setQuotes] = useState<ApiQuote[]>([]);
  const [filter, setFilter] = useState<string[]>([]);
  //when my filter and allquotes change my quotes get updated
  useEffect(() => {
    if (filter.length > 0) {
      setQuotes(
        allQuotes.filter((quote) =>
          quote.tags.some((tag) => filter.includes(tag))
        )
      );
    } else {
      setQuotes(allQuotes);
    }
  }, [filter, allQuotes]);
  //fetching new quote
  const fetchnewData = () => {
    fetch("https://api.quotable.io/quotes/random")
      .then((response) => response.json())
      .then((data: ApiQuote[]) => {
        setAllQuotes([...data, ...allQuotes]);
      });
  };
  //fethicng 3 quotes at page load
  const fetchData = () => {
    fetch("https://api.quotable.io/quotes/random?limit=3")
      .then((response) => response.json())
      .then((data: ApiQuote[]) => {
        setAllQuotes(data);
      });
  };
  //on window load
  useEffect(() => {
    fetchData();
  }, []);
  //on deleting filter
  const deletefilter = (q: string) => {
    const updatedFilter = filter.filter((q1) => q1 !== q);
    setFilter(updatedFilter); // Update filter
  };

  return (
    <div className="container">
      <button onClick={fetchnewData} className="newitem">
        New Quote
      </button>

      <div className="filters">
        {/* Display filters */}
        <h1>Filters</h1>
        {filter.map((f) => (
          <div key={f} className="filterscontent">
            <span>{f}</span>
            <span className="delete" onClick={() => deletefilter(f)}>
              X
            </span>
          </div>
        ))}
      </div>

      {/* Display quotes */}
      {quotes.map((quote) => (
        <Quote key={quote._id} quote={quote} updateFilter={setFilter} />
      ))}
    </div>
  );
}

export default App;
