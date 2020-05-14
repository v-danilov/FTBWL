export const END_POINTS = {
  GET_ALL: {
    EVENTS: '/events',
    GAME_SYSTEMS: '/dictionaries/gameSystems'
  },
  EVENTS: {
    DEFAULT: '/events/',
    UPDATE_STATUS: '/events/{id}/status',
    SAVE_PLAYERS: '/event/{id}/savePlayers'
  },
  AUTHENTICATION: {
    REGISTRATION: '/users',
    AUTHENTICATE: '/authenticate'
  },
  PAIRINGS_BY_ROUND: {
    DEFAULT: '/pairings?roundID='
  }
}
