export const END_POINTS = {
  GET_ALL: {
    EVENTS: '/events',
    GAME_SYSTEMS: '/dictionaries/gameSystems'
  },
  EVENTS: {
    BY_ID: '/events/',
    REG_USER: '/registrationRequest',
    UPDATE_STATUS: '/events/{id}/status',
    CREATE: '/event/create',
    // TODO replace parameters
    SAVE_PLAYERS: '/event/{id}/savePlayers'
  },
  AUTHORIZATION: {
    SIGN_UP: '/api/signUp',
    SIGN_IN: '/signIn',
    GET_CITIES_BY_PREFIX: '/api/getCitiesByPrefix'
  }
}
