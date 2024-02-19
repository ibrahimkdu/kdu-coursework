import Landingpage from "./portfolio/Landingpage";
import "./App.css";

function App() {
  // api call
  const api = {
    name: "Amey",
    fullName: "Amey Aditya",
    qualification: "SSE",
    skills: [
      {
        id: 1,
        skill: "Python",
      },
      {
        id: 2,
        skill: "React",
      },
    ],
    hobbies: [
      {
        id: 1,
        hobby: "Cricket",
      },
    ],
  };
  return (
    <div>
      {/* component landingpage starts*/}
      <Landingpage api={api} />
      {/* component landing page ends */}
    </div>
  );
}

export default App;
