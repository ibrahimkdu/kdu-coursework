import { ApiQuote } from "./types/quotes.types";
import "./Quote.scss";
import { useState } from "react";
//interface to get the props from parent
interface QuoteProps {
  quote: ApiQuote;
  updateFilter: (filter: string[]) => void;
}

export function Quote({ quote, updateFilter }: QuoteProps) {
  const [filter, setFilter] = useState<string[]>([]);
//function to add the filter
  const addfilter = (q: string) => {
    // setFilter([...filter, q]);
    updateFilter([...filter, q]);
  };

  return (
    <div className="quote">
      <div className="quotecontent">
        <h1>{quote.content}</h1>
      </div>
      <div className="details">
        <div className="authorname">
          <p>~{quote.author}</p>
        </div>
        <div className="date">
          <p>{quote.dateAdded}</p>
        </div>
      </div>
       {/* Mapping through the tags */}
      <div className="tagcontainer">
        {quote.tags.map((q) => (
          <div key={q} className="tag">
            <span onClick={() => addfilter(q)}>{q}</span>
          </div>
        ))}
      </div>
    </div>
  );
}
