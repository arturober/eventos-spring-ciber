"use strict";

document.querySelector("evento-detail").remove();
const container = document.querySelector("div.container");
const h1 = document.createElement("h1");
h1.append("You have been PWNED!");

const token = localStorage.token;
const p = document.createElement("p");
p.append(`Tus credenciales son: ${token}. A lo mejor me las quedo y accedo con tu usuario :-P`);

container.append(h1, p);
