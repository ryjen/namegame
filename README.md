# Name Game

Guess the user

---

A demo project to display a list of random users to try and guess the name.

---

## Architecture

A clean architecture MVVM with Flux interactors.

### Flux

A single dispatcher for all data flow between data stores. See [flux in depth](https://facebook.github.io/flux/docs/in-depth-overview).

#### Dispatcher

The global dispatcher simply forwards actions to all stores.

Stores are created from the dispatcher with a listener pre-enabled.

#### Stores

Stores hold the state for a view model and respond to actions.

Typically a reducer makes state modifications and effects control non-state secondary actions.

#### Actions

Simply flags and parameters to manipulate application state and effects.

#### State

State has two forms:

1. inside the view model store for logic
2. mapped to composables for display

### Data

Repositories with multiple data sources map data to the business domain.

### Domain

Domain contains business models and logic. Use cases perform complex logic for UI layer.

### UI

Jetpack compose that renders with state flows from the view models.

Navigation, top/bottom bars, and user actions are controlled with a dispatcher from the viewmodel.
