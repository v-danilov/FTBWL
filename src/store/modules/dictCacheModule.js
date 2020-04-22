import { HTTPResponseStatusConstants } from '../../components/util/constants/CommonConstants'
import axios from 'axios'
import { store } from '@/store/store.js'
import { NOTIFICATION_TYPES } from '@/components/notifications/notificationTypes'
/* global state, getters, mutations, actions  */
/* exported state, getters, mutations, actions */
const state = {
  gameSystems: localStorage.getItem('gameSystems') || [],
  fractions: localStorage.getItem('fractions') || [],
  formats: localStorage.getItem('formats') || [],
  countries: localStorage.getItem('countries') || [],
  cities: localStorage.getItem('cities') || [],
  places: localStorage.getItem('places') || [],
  organizers: localStorage.getItem('organizers') || [],
  statuses: localStorage.getItem('statuses') || [],
  dictsLoaded: localStorage.getItem('dictsLoaded') || false
}
const getters = {
  cachedGameSystems: state => state.gameSystems,
  cachedFactions: state => state.factions,
  cachedFormats: state => state.formats,
  cachedCountries: state => state.countries,
  cachedCities: state => state.cities,
  cachedPlaces: state => state.places,
  cachedOrganizers: state => state.organizers,
  cachedStatuses: state => state.statuses,
  cachedRulePacks: state => state.rulePacks,
  dictsLoadedFlag: state => state.dictsLoaded
}
// this.$store.commit(“SET_USER”, user) synchronous
const mutations = {
  SET_GAME_SYSTEMS: (state, payload) => {
    state.gameSystems = payload
  },
  SET_FACTIONS: (state, payload) => {
    state.factions = payload
  },
  SET_FORMATS: (state, payload) => {
    state.formats = payload
  },
  SET_COUNTRIES: (state, payload) => {
    state.countries = payload
  },
  SET_CITIES: (state, payload) => {
    state.cities = payload
  },
  SET_PLACES: (state, payload) => {
    state.places = payload
  },
  SET_ORGANIZERS: (state, payload) => {
    state.organizers = payload
  },
  SET_STATUSES: (state, payload) => {
    state.statuses = payload
  },
  SET_RULEPACKS: (state, payload) => {
    state.rulePacks = payload
  },
  SET_DISCTS_LOADED_FLAG: (state, payload) => {
    state.dictsLoaded = payload
  }
}
// this.$store.dispatch("SET_USER",user) asynchronous

const actions = {
  INIT_DICTS: (context, url) => {
    axios.get(url)
      .then(response => {
        context.commit('SET_PLACES', response.data.places)
        commitAsMap('SET_FACTIONS', 'systemName', context, response.data.factions)
        commitAsMap('SET_FORMATS', 'code', context, response.data.eventFormats)
        commitAsMap('SET_STATUSES', 'code', context, response.data.eventStatuses)
        commitAsMap('SET_RULEPACKS', 'code', context, response.data.rulePacks)
        context.commit('SET_DISCTS_LOADED_FLAG', true)
      }).catch(reason => {
        store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Failed to download dictionaries 🤷‍♂️'})
      })
  },
  INIT_GAME_SYSTEMS: (context, url) => {
    return axios.get(url).then(response => {
      commitAsMap('SET_GAME_SYSTEMS', 'code', context, response.data)
      return response.data
    }).catch(reason => {
      store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Failed to download game systems dictionary 🤷‍♂️'})
    })
  },
  INIT_FACTIONS: (context, url) => {
    axios.get(url).then(response => {
      commitAsMap('SET_FACTIONS', 'systemName', context, response.data)
    }).catch(reason => {
      store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Failed to download fractions dictionary 🤷‍♂️'})
    })
  },
  INIT_FORMATS: (context, url) => {
    axios.get(url)
      .then(response => {
        if (response.status === HTTPResponseStatusConstants.OK) {
          commitAsMap('SET_FORMATS', 'code', context, response.data)
        }
      }).catch(reason => {
        store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Failed to download formats dictionary 🤷‍♂️'})
      })
  },
  INIT_COUNTRIES: (context, url) => {
    axios.get(url)
      .then(response => {
        context.commit('SET_COUNTRIES', response.data.payload)
      }).catch(reason => {
        store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Failed to download countries dictionary 🤷‍♂️'})
      })
  },

  INIT_CITIES: (context, url) => {
    axios.get(url)
      .then(response => {
        context.commit('SET_CITIES', response.data.payload)
      }).catch(reason => {
        store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Failed to download cities dictionary 🤷‍♂️'})
      })
  },
  INIT_PLACES: (context, url) => {
    axios.get(url)
      .then(response => {
        context.commit('SET_PLACES', response.data.payload)
      }).catch(reason => {
        store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Failed to download places dictionary 🤷‍♂️'})
      })
  },
  INIT_ORGANIZERS: (context, url) => {
    axios.get(url)
      .then(response => {
        context.commit('SET_ORGANIZERS', response.data.payload)
      }).catch(reason => {
        store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Failed to download organizers dictionary 🤷‍♂️'})
      })
  },
  INIT_STATUSES: (context, url) => {
    axios.get(url)
      .then(response => {
        commitAsMap('SET_STATUSES', 'code', context, response.data)
      }).catch(reason => {
        store.dispatch('notifications/add', {type: NOTIFICATION_TYPES.ERROR, text: 'Failed to download statuses dictionary 🤷‍♂️'})
      })
  }
}
function commitAsMap (setter, keyField, context, payload) {
  const payloadAsMap = new Map()
  payload.forEach(e => payloadAsMap.set(e[keyField], e))
  context.commit(setter, payloadAsMap)
}

export default {
  state, getters, mutations, actions
}
