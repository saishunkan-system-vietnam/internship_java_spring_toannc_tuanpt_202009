<template>
  <v-container style="font-family: time new roman">
    <v-row>
      <v-col cols="12" sm="2"> </v-col>
      <v-col cols="12" sm="8">
        <v-card>
          <v-row>
            <v-col cols="12" sm="3">
              <v-img
                style=""
                lazy-src="https://picsum.photos/id/11/10/6"
                max-height="150"
                max-width="250"
                :src="baseUrl + profile.avatar"
              ></v-img>
            </v-col>
            <v-col>
              <h2>
                <b>{{ profile.name }}</b>
              </h2>
              <v-avatar tile v-if="Object.keys(team).length!=0">
                <img :src="baseUrl + team.logo" alt="John" />
              </v-avatar>
              <h3 style="display: inline-block">
                <b>{{ Object.keys(team).length!=0?team.nameTeam:'No Team' }}</b>
              </h3>
              <h3>
                Position:<b>{{ profile.position }}</b>
              </h3>
              <h5 style="font-family: time new roman; font-size: 20px">
                Tournament:<b>{{ Object.keys(team).length!=0?team.tourName:"No tournament" }}</b>
              </h5>
            </v-col>
            <v-col>
              <h3>
                Country:<b>{{ profile.country }}</b>
              </h3>
              <h3>
                Age:<b>{{ profile.currentAge }}</b>
              </h3>
              <h3>
                Sex:<b>{{ profile.gender }}</b>
              </h3>
              <h3>
                Phone:<b>{{ profile.phone }}</b>
              </h3>
            </v-col>
          </v-row>
        </v-card>
        <v-card style="margin-top: 50px">
          <v-card-title><b style="color: red"> Result</b></v-card-title>
          <v-simple-table>
            <template v-slot:default>
              <thead>
                <tr>
                  <th class="text-left">Tournament</th>
                  <th class="text-left">Date</th>
                  <th class="text-left">Event</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(item, i) in lastResultsData"
                  :key="i"
                  @click="detailSchedule(item)"
                >
                  <template v-if="i < 5">
                    <td>{{ item.nameTour }}</td>
                    <td>
                      {{ item.dayStart }} {{item.monthStart.substr(item.monthStart.length-6)}}
                    </td>
                    <td>
                      <v-row>
                        <v-col>
                          <v-avatar tile>
                            <img
                              :src="baseUrl + item.logoTeam1"
                              alt="John"
                            /> </v-avatar></v-col
                        ><v-col> {{ item.nameTeam1 }}</v-col
                        ><v-col
                          ><b>{{ item.score1 }} - {{ item.score2 }}</b></v-col
                        ><v-col> {{ item.nameTeam2 }}</v-col
                        ><v-col>
                          <v-avatar tile>
                            <img
                              :src="baseUrl + item.logoTeam2" 
                              alt="John"
                            /> </v-avatar
                        ></v-col>
                      </v-row>
                    </td>
                  </template>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-card>
        <v-card style="margin-top: 50px">
          <v-card-title><b style="color: red"> Schedule</b></v-card-title>
          <v-row class="fill-height">
            <v-col>
              <v-sheet height="64">
                <v-toolbar flat>
                  <v-btn
                    outlined
                    class="mr-4"
                    color="grey darken-2"
                    @click="setToday"
                  >
                    Today
                  </v-btn>
                  <v-btn fab text small color="grey darken-2" @click="prev">
                    <v-icon small> mdi-chevron-left </v-icon>
                  </v-btn>
                  <v-btn fab text small color="grey darken-2" @click="next">
                    <v-icon small> mdi-chevron-right </v-icon>
                  </v-btn>
                  <v-toolbar-title v-if="$refs.calendar">
                    {{ $refs.calendar.title }}
                  </v-toolbar-title>
                  <v-spacer></v-spacer>
                </v-toolbar>
              </v-sheet>
              <v-sheet height="600">
                <v-calendar
                  ref="calendar"
                  v-model="focus"
                  color="primary"
                  :events="events"
                  @click:event="showEvent"
                ></v-calendar>
                <v-menu
                  v-model="selectedOpen"
                  :close-on-content-click="false"
                  :activator="selectedElement"
                  offset-x
                >
                  <v-card color="grey lighten-4" min-width="350px" flat>
                    <v-toolbar :color="selectedEvent.color" dark>
                      <v-toolbar-title
                        ><a
                          :href="
                            $router.resolve({
                              path:
                                '/tournamentDetail/' +
                                selectedEvent.idTour +
                                '/team',
                            }).href
                          "
                          >{{ selectedEvent.tournament }}</a
                        >
                      </v-toolbar-title>
                      <v-spacer></v-spacer>
                    </v-toolbar>
                    <v-card-text>
                      <a
                        :href="
                          $router.resolve({
                            path: '/summary/' + selectedEvent.idSchedule,
                          }).href
                        "
                      >
                        <span v-html="selectedEvent.name"></span>
                        <div>
                          <v-avatar tile>
                            <img
                              :src="baseUrl + selectedEvent.logo"
                              alt="John"
                            />
                          </v-avatar>
                        </div>
                      </a>
                    </v-card-text>
                    <v-card-actions>
                      <v-btn
                        text
                        color="secondary"
                        @click="selectedOpen = false"
                      >
                        Cancel
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-menu>
              </v-sheet>
            </v-col>
          </v-row>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import { ENV } from "@/config/env.js";

export default {
  computed: {
    baseUrl() {
      return ENV.BASE_IMAGE;
    },
  },
  data: () => ({
    focus: "",
    team: "",
    profile: "",
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
    lastResultsData: [],
    schedule: [],
    events: [],
  }),
  created() {
    this.profile = this.$store.state.user.userInfo.profile;
    this.lastResults();
    this.getTeam();
    this.scheduleTeam();
  },

  methods: {
    prev() {
      this.$refs.calendar.prev();
    },
    setToday() {
      this.focus = "";
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.selectedEvent = event;
        this.selectedElement = nativeEvent.target;
        setTimeout(() => {
          this.selectedOpen = true;
        }, 10);
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        setTimeout(open, 10);
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },

    rnd(a, b) {
      return Math.floor((b - a + 1) * Math.random()) + a;
    },
    getTeam() {
      this.$store.dispatch("team/getTeamById", this.profile.idTeam).then((response) => {
        if (response.data.code == 0) {
          this.team = response.data.payload;
        } else {
          this.team = {};
        }
      });
    },
    lastResults() {
      let self = this;
      this.$store
        .dispatch(
          "member/lastFiveMatch",
          this.$store.state.user.userInfo.profile.id
        )
        .then((response) => {
          let data = response.data;
          if (data.code == 0) {
            self.lastResultsData = response.data.payload;
            // console.log(response.data.payload);
          } else {
            self.lastResultsData = [];
            // alert(data.message);
          }
        })
        .catch(function (error) {
          alert(error);
        });
    },
    detailSchedule(item) {
      // console.log(item);
      this.$router.push("/summary/" + item.idSchedule);
    },
    async scheduleTeam() {
      await this.$store
        .dispatch("schedule/scheduleTeam", this.profile.idTeam)
        .then((response) => {
          this.schedule = response.data.payload;
        });
      this.schedule.forEach((element) => {
        element.team.forEach((item) => {
          if (item.idTeam != this.profile.idTeam) {
            this.events.push({
              start: element.timeStart,
              timed: true,
              name: "VS " + item.nameTeam,
              logo: item.logo,
              idTour: element.idTour,
              tournament: element.tournament.nameTournament,
              idSchedule: element.idSchedule,
            });
          }
        });
      });
    },
  },
};
</script>