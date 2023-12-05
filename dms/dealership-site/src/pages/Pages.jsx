import React from "react";
import "./Pages.css";


export function InfoCell({header, value}) {
  return (
    <div className="InfoCell">
      <b>{header}</b>&nbsp;
      {value}
    </div>
  );
}

