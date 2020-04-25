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
    SIGN_UP: '/api/register',
    AUTHENTICATE: '/authenticate'
  },
  PAIRINGS_BY_ROUND: {
    DEFAULT: '/pairings?roundID='
  }
}
