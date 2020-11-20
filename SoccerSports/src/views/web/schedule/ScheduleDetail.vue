<template>
  <div>
    <v-hover>
      <v-card>
        <v-card class="text-center">
          <h1
            style="font-family: times; color: blue; cursor: pointer"
            @click="detailTournament(schedule.tournament.idTournament)"
          >
            {{
              Object.keys(schedule).length === 0
                ? ""
                : schedule.tournament.nameTournament
            }}
          </h1>
        </v-card>
        <v-row style="margin-top: 40px">
          <v-col class="text-center" cols="12" sm="3">
            <v-avatar size="128" tile
              ><img
                :src="
                  Object.keys(schedule).length === 0
                    ? ''
                    : baseUrl + schedule.team[0].logo
                "
                alt="Logo" /></v-avatar
          ></v-col>
          <v-col>
            <v-row>
              <v-col
                ><v-row
                  ><v-col
                    ><v-row
                      ><h2
                        @click="detailTeam(schedule.team[0])"
                        style="cursor: pointer"
                      >
                        {{
                          Object.keys(schedule).length === 0
                            ? ""
                            : schedule.team[0].nameTeam
                        }}
                      </h2>
                      <v-avatar size="50"
                        ><img
                          :src="
                            Object.keys(schedule).length === 0
                              ? ''
                              : baseUrl + schedule.team[0].logo
                          "
                          alt="Logo" /></v-avatar></v-row
                    ><v-row style="display: block">
                      <div v-for="(item, i) in goal1" :key="i">
                        <v-icon dark color="red"> mdi-soccer </v-icon
                        >{{ goal1.length > 0 ? item.profile.name : "" }}({{
                          goal1.length > 0
                            ? item.time.substring(0, 5) + "&nbsp;"
                            : ""
                        }})
                      </div>
                    </v-row></v-col
                  ></v-row
                ></v-col
              >
              <v-col class="text-center">
                <v-row>
                  <v-col
                    ><h2>
                      {{ schedule.status == 2 ? schedule.score1 : "" }}
                    </h2></v-col
                  >
                  <v-col><h2>FT</h2></v-col>
                  <v-col
                    ><h2>
                      {{ schedule.status == 2 ? schedule.score2 : "" }}
                    </h2></v-col
                  >
                </v-row>
                {{
                  Object.keys(schedule).length === 0
                    ? ""
                    : schedule.timeStart.substring(0, 10)
                }}<br />
                {{
                  Object.keys(schedule).length === 0
                    ? ""
                    : schedule.timeStart.substring(11, 16)
                }}
              </v-col>
              <v-col class="text-right"
                ><v-row
                  ><v-col
                    ><v-row style="display: flex; justify-content: flex-end">
                      <v-avatar size="50"
                        ><img
                          :src="
                            Object.keys(schedule).length === 0
                              ? ''
                              : baseUrl + schedule.team[1].logo
                          "
                          alt="Logo"
                      /></v-avatar>
                      <h2
                        @click="detailTeam(schedule.team[1])"
                        style="cursor: pointer"
                      >
                        {{
                          Object.keys(schedule).length === 0
                            ? ""
                            : schedule.team[1].nameTeam
                        }}
                      </h2>
                    </v-row>
                    <span v-for="(item, i) in goal2" :key="i"
                      ><v-icon dark color="red"> mdi-soccer </v-icon
                      >{{ item.profile.name }}({{
                        item.time.substring(0, 5)
                      }})<br
                    /></span> </v-col></v-row
              ></v-col>
            </v-row>
          </v-col>
          <v-col class="text-center" cols="12" sm="3">
            <v-avatar size="128" tile
              ><img
                :src="
                  Object.keys(schedule).length === 0
                    ? ''
                    : baseUrl + schedule.team[1].logo
                "
                alt="Logo" /></v-avatar
          ></v-col>
        </v-row>
      </v-card>
    </v-hover>

    <v-container>
      <nav id="nav">
        <router-link
          :to="{
            path: `/summary/${schedule.idSchedule}`,
          }"
          >Summary</router-link
        >

        <router-link
          :to="{
            path: `/statistics/${schedule.idSchedule}`,
          }"
          >Statistics</router-link
        >

        <template v-if="schedule.status == 2">
          <router-link
            :to="{
              path: `/video/${schedule.idSchedule}`,
            }"
            >Video-photo</router-link
          >
        </template>
      </nav>
      <hr />
    </v-container>
    <section>
      <router-view />
    </section>
  </div>
</template>
<script>
import { ENV } from "@/config/env.js";

export default {
  data() {
    return {
      schedule: {},
      goal1: [],
      goal2: [],
    };
  },
  computed: {
    baseUrl() {
      return ENV.BASE_IMAGE;
    },
  },
  created() {
    this.getData();
  },

  methods: {
    detailTeam(item) {
      this.$router.push({
        path: `/team/${item.idTeam}`,
        query: { idTab: 1 },
      });
    },
    getData() {
      console.log(this.$route.params.id);
      this.$store
        .dispatch("schedule/getById", this.$route.params.id)
        .then((response) => {
          if (response.data.code == 0) {
            this.schedule = response.data.payload;
            console.log(this.schedule);
            this.getDataGoal();
          }
        });
    },
    detailTournament(item) {
      this.$router.push("/tournamentDetail/" + item+"/team");
    },
    getDataGoal() {
      this.goal1 = [];
      this.goal2 = [];
      this.schedule.goal.forEach((element) => {
        if (element.team == 1) {
          this.schedule.team[0].profile.forEach((profile) => {
            if (profile.id == element.idMember) {
              this.goal1.push({
                profile: profile,
                time: element.time,
                idSchedule: this.schedule.idSchedule,
              });
            }
          });
        }
        if (element.team == 2) {
          this.schedule.team[1].profile.forEach((profile) => {
            if (profile.id == element.idMember) {
              this.goal2.push({
                profile: profile,
                time: element.time,
                idSchedule: this.schedule.idSchedule,
              });
            }
          });
        }
      });
    },
  },
};
</script>
<style scoped>
#nav {
  display: flex;
  align-items: center;
}
#nav a {
  font-weight: bold;
  color: red;
  padding: 0 10px;
}
#nav a.vue-school-active-class {
  color: green;
}
</style>