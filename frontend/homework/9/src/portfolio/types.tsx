// interfaces.ts
export interface IApi {
    name: string;
    fullName: string;
    qualification: string;
    skills: ISkill[];
    hobbies: IHobbies[];
  }
  
  export interface ISkill {
    id: number;
    skill: string;
  }
  
  export interface IHobbies {
    id: number;
    hobby: string;
  }
  