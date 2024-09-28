import { IApi } from "./types";
import "./landingpage.css";
//interface DetailsProps for fetching data in required format
interface DetailsProps {
  detailapi: IApi;
}

function Details({ detailapi }: DetailsProps) {
  return (
    //fetching details from DetailsProps
    <div className="details">
      <p>{detailapi.name}</p>
      <p>{detailapi.fullName}</p>
      <p>{detailapi.qualification}</p>
    </div>
  );
}

export default Details;
