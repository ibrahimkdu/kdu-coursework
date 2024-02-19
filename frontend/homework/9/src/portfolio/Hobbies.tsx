import { IHobbies } from "./types";
import "./landingpage.css";
//interface for HobbiesProps in required format
interface HobbiesProps {
  hobbieapi: IHobbies[];
}

function Hobbies({ hobbieapi }: HobbiesProps) {
  return (
    //fethcing each hobbie from HobbieProps
    <div className="hobbies">
      <h1>Hobbies</h1>
      {hobbieapi.map((hobby) => (
        <div key={hobby.id}>{hobby.hobby}</div>
      ))}
    </div>
  );
}

export default Hobbies;
