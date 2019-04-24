// ouverture de la socket pour communiquer avec le serveur
let socket = new WebSocket("ws://localhost:3232");

class Main extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            supply_data: null,
            players_data: null,
            turn_player: null,
            active_player: null,
            instruction: null,
            choices: null
        };
    }

    componentDidMount() {
        socket.onmessage = event => {
            let data = JSON.parse(event.data);
            console.log(data);
            this.setState({
                supply_data: data['game']['supply'],
                players_data: data['game']['players'],
                turn_player: data['game']['turn_player'],
                active_player: data['active_player'],
                instruction: data['instruction'],
                choices: data['choices']
            });
        };
    }

    render() {
        if (this.state.supply_data === null) {
            return React.createElement(
                'div',
                null,
                'Not connected to an active game.'
            );
        }
        return React.createElement(
            'div',
            { id: 'main' },
            React.createElement(
                'div',
                { id: 'supply' },
                React.createElement(
                    'div',
                    { id: 'kingdom_supply' },
                    this.state.supply_data.slice(0, -7).map(item => {
                        if (item['number'] === 0) {
                            return React.createElement(Card, { classes: ['phantom', 'half'] });
                        } else {
                            return React.createElement(Card, { name: item['card'], number: item['number'], cost: item['cost'], classes: ['half'] });
                        }
                    })
                ),
                React.createElement(
                    'div',
                    { id: 'common_supply' },
                    this.state.supply_data.slice(-7).map(item => {
                        if (item['number'] === 0) {
                            return React.createElement(Card, { classes: ["phantom"] });
                        } else {
                            return React.createElement(Card, { name: item['card'], number: item['number'], cost: item['cost'] });
                        }
                    })
                )
            ),
            React.createElement(
                'div',
                { id: 'players' },
                this.state.players_data.map((player_data, index) => {
                    let is_active = index === this.state.active_player;
                    let is_turn_player = index === this.state.turn_player;
                    let instruction = is_active ? this.state.instruction : "";
                    let choices = is_active ? this.state.choices : [];
                    return React.createElement(Player, {
                        data: player_data,
                        is_active: is_active,
                        is_turn_player: is_turn_player,
                        instruction: instruction,
                        choices: choices,
                        game_over: this.state.active_player === undefined
                    });
                })
            )
        );
    }
}

function Card(props) {
    let classes = ['card'];
    if (props.classes) {
        classes.push(...props.classes);
    }

    let short_name;
    if (props.name) {
        short_name = props.name.toLowerCase().replace(/[^a-z]/, '');
    }

    return React.createElement(
        'div',
        {
            className: classes.join(' '),
            style: props.name && { backgroundImage: `url(cards/${short_name}.jpg)` },
            onMouseEnter: props.name && function () {
                const url = `url(cards/${short_name}.jpg)`;
                document.getElementById('zoom').setAttribute('style', `background-image: ${url}`);
            },
            onMouseLeave: props.name && function () {
                document.getElementById('zoom').setAttribute('style', '');
            },
            onClick: props.name && function () {
                socket.send(props.name);
            }
        },
        props.cost !== undefined && React.createElement(
            'div',
            { className: 'cost' },
            props.cost
        ),
        props.number && React.createElement(
            'div',
            { className: 'number' },
            props.number
        )
    );
}

function Player(props) {
    let data = props['data'];
    let classes = ["player"];
    if (props.is_active) {
        classes.push("active");
    }
    if (props.is_turn_player) {
        classes.push("turn");
    }
    data['hand'].sort();
    console.log(props);
    return React.createElement(
        'div',
        { className: classes.join(" ") },
        React.createElement(
            'div',
            { className: 'player_info' },
            React.createElement(
                'div',
                { className: 'name' },
                data['name']
            ),
            React.createElement(
                'div',
                { className: 'data' },
                `Money: ${data['money']} Actions: ${data['actions']} Buys: ${data['buys']} Draw: ${data['draw']} Discard: ${data['discard']}`
            )
        ),
        React.createElement(
            'div',
            { className: 'instruction' },
            props.instruction,
            props.choices.map(choice => {
                return React.createElement(
                    'button',
                    { onClick: () => socket.send(choice) },
                    choice
                );
            }),
            React.createElement(
                'button',
                { onClick: () => socket.send("") },
                'Pass'
            )
        ),
        !props.game_over && React.createElement(ListOfCards, { className: 'in_play', cards: data['in_play'] }),
        React.createElement(ListOfCards, { className: 'hand', cards: data['hand'] })
    );
}

function ListOfCards(props) {
    if (props.cards.length === 0) {
        return React.createElement(
            'div',
            { className: props.className },
            React.createElement(Card, { classes: ["phantom"] })
        );
    }
    return React.createElement(
        'div',
        { className: props.className },
        props.cards.map((card, i) => {
            if (card === props.cards[i + 1]) {
                return React.createElement(Card, { name: card, classes: ['duplicate'] });
            } else {
                return React.createElement(Card, { name: card });
            }
        })
    );
}

/**
 * Envoie un message vide au serveur pour indiquer que le joueur passe
 */
function pass() {
    socket.send("");
}

ReactDOM.render(React.createElement(Main, null), document.getElementById('root'));
