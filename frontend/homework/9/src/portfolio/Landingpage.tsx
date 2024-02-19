// LandingPage.tsx
import React from "react";
import Details from "./Details";
import Hobbies from "./Hobbies";
import Skills from "./Skills";
import { IApi } from "./types";
import "./landingpage.css";
//declaring props interface to fetch data in required format
interface LandingPageProps {
  api: IApi;
}

function LandingPage({ api }: LandingPageProps) {
  return (
    <div className="landing">
      {/* Details component starts */}
      <Details detailapi={api} />
      {/* Details component ends */}
      <div className="mid">
        {/* Skills component starts */}
        <Skills skillapi={api.skills} />
        {/* SKills components ends */}
        {/* Hobbies component starts */}
        <Hobbies hobbieapi={api.hobbies} />
        {/* Hobbies component ends */}
      </div>
    </div>
  );
}

export default LandingPage;
