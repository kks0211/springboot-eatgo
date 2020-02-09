(async () => {
    const url = "http://localhost:8080/restaurants";
    const response = await fetch(url);
    const data = await response.json();

    const element = document.getElementById('app');
    element.innerHTML = `
                ${data.map(restaurant =>`
                    <p>
                         ${data.id}
                         ${data.name}
                         ${data.address}
                    </p>`
                ).join('')}
        `;
})();