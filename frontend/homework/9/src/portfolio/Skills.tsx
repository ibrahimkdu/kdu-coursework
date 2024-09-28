import { ISkill } from "./types";
import "./landingpage.css";
//interface for skillsprop for getting the prop in requiref format
interface SkillsProps {
  skillapi: ISkill[];
}

function Skills({ skillapi }: SkillsProps) {
  return (
    //fetching each data from akillsprop
    <div className="skills">
      <h1>SKILLS</h1>
      {skillapi.map((skill) => (
        <div key={skill.id}>{skill.skill}</div>
      ))}
    </div>
  );
}

export default Skills;
