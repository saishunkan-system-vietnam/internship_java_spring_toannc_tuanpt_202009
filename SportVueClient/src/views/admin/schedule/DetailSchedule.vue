<template>
  <div>
    <div>
      <v-breadcrumbs :items="itemlinks" large>
        <template v-slot:divider>
          <v-icon>mdi-chevron-right</v-icon>
        </template>
      </v-breadcrumbs>
      <div v-if="schedule.status == 2">
        <div v-if="schedule.team[0].type == 'Football'">
          <v-btn color="green darken-1" text @click="dialogFootball = true">
            <b-icon-upload></b-icon-upload>update
          </v-btn>
        </div>
        <div v-if="schedule.team[0].type == 'TableTennis'">
          <v-btn color="green darken-1" text @click="dialogTableTennis = true"
            ><b-icon-upload></b-icon-upload>update</v-btn
          >
        </div>
        <div v-if="schedule.team[0].type == 'Basketball'">
          <v-btn color="green darken-1" text @click="dialogBasketBall = true"
            ><b-icon-upload></b-icon-upload>update</v-btn
          >
        </div>
      </div>
      <div v-else>
        <div class="alert alert-danger" role="alert">the match is not over</div>
      </div>
    </div>
    <v-row justify="center">
      <v-dialog v-model="dialogFootball" width="900px">
        <v-card>
          <v-card-title class="headline"> Update Football </v-card-title>
          <v-card-text>
            <UpdateScheduleFootBall
              :schedule="schedule"
              :loadData="getData"
              :callback="hideModal"
            />
          </v-card-text>
        </v-card>
      </v-dialog>
      <v-dialog v-model="dialogBasketBall" width="900px">
        <v-card>
          <v-card-title class="headline"> Update BasketBall </v-card-title>
          <v-card-text>
            <UpdateScheduleBasketBall
              :schedule="schedule"
              :loadData="getData"
              :callback="hideModal"
            />
          </v-card-text>
        </v-card>
      </v-dialog>
      <v-dialog v-model="dialogTableTennis" width="900px">
        <v-card>
          <v-card-title class="headline"> Update TableTennis </v-card-title>
          <v-card-text>
            <UpdateScheduleTableTennis
              :schedule="schedule"
              :loadData="getData"
              :callback="hideModal"
            />
          </v-card-text>
        </v-card>
      </v-dialog>
    </v-row>
    <b-container>
      <h1 style="color: red;cursor: pointer" @click="detailTour(schedule.idTour)">Tournament :{{ schedule.nameTour }}</h1>
      <h3>{{ schedule.title }}</h3>
      <h5>Time:{{ schedule.timeStart }}</h5>
      <br /><br /><br />
      <b-row>
        <b-col>
          <h3 style="color: Blue">Team 1: {{ team1.nameTeam }}</h3>
          <b-img :src="url+team1.logo" fluid alt="Responsive image"></b-img>
        </b-col>
        <b-col>
          <h3 style="color: Blue">Team 2: {{ team2.nameTeam }}</h3>
          <b-img :src="url+team2.logo" fluid alt="Responsive image"></b-img>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <h4 style="color: Blue">Member:</h4>
          <ul v-for="(item, index) in team1.profile" v-bind:key="index">
            <li
              style="color: #194810; cursor: pointer"
              @click="detailMember(item.email)"
            >
              {{ item.name }} - {{ item.age }} year old -{{ item.address }}
            </li>
          </ul>
        </b-col>
        <b-col>
          <h4 style="color: Blue">Member :</h4>
          <ul v-for="(item, index) in team2.profile" v-bind:key="index">
            <li
              style="color: #194810; cursor: pointer"
              @click="detailMember(item.email)"
            >
              {{ item.name }} - {{ item.age }} year old -{{ item.address }}
            </li>
          </ul>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <h4 style="color: Blue">Infomation :</h4>
          <ul>
            <li>Win rate : {{ inforTeam1.rate }}%</li>
            <li>
              Total number of games in the tournament :
              {{ inforTeam1.sumJoinByTour }}
            </li>
            <li>
              Total number of wins in the tournament :
              {{ inforTeam1.sumWinJoinByTour }}
            </li>
            <li>Total number of matches : {{ inforTeam1.sum }}</li>
            <li>Total number of wins : {{ inforTeam1.sumWin }}</li>
          </ul></b-col
        >
        <b-col
          ><h4 style="color: Blue">Infomation :</h4>
          <ul>
            <li>Win rate : {{ inforTeam2.rate }}%</li>
            <li>
              Total number of games in the tournament :
              {{ inforTeam2.sumJoinByTour }}
            </li>
            <li>
              Total number of wins in the tournament :
              {{ inforTeam2.sumWinJoinByTour }}
            </li>
            <li>Total number of matches : {{ inforTeam2.sum }}</li>
            <li>Total number of wins : {{ inforTeam2.sumWin }}</li>
          </ul></b-col
        >
      </b-row>
      <div>
        <div>
          <b-card no-body>
            <b-tabs>
              <b-tab
                :title="item.roundName"
                v-for="(item, index) in schedule.round"
                v-bind:key="index"
              >
                <b-card-text>
                  Score :
                  <b-row>
                    <b-col class="text-center"
                      ><b-img :src="url+team1.logo" width="100px"></b-img
                    ></b-col>
                    <b-col class="text-center"
                      ><h1>
                        {{ item.roundScore1 }}-{{ item.roundScore2 }}
                      </h1></b-col
                    >
                    <b-col class="text-center"
                      ><b-img :src="url+team2.logo" width="100px"></b-img
                    ></b-col>
                  </b-row>
                  Description:
                  <b-form-textarea
                    id="textarea-no-resize"
                    rows="5"
                    no-resize
                    disabled
                    :value="item.roundDescription"
                  ></b-form-textarea>
                </b-card-text>
              </b-tab>
            </b-tabs>
          </b-card>
        </div>
      </div>
      <h2 style="color: blue">Summary</h2>
      <b-form-textarea
        id="textarea-no-resize"
        rows="5"
        no-resize
        v-model="schedule.description"
        disabled
      ></b-form-textarea>
      <h4>Final score : {{ schedule.scoreTeam1 }}-{{ schedule.scoreTeam2 }}</h4>
      <div
        class="text-center"
        v-if="schedule.image != null && schedule.video != null"
      >
        <h3 style="color: Blue">Photo and Video</h3>
        <h5>Photo</h5>
        <b-img
          :src="url+schedule.image"
          alt="Responsive image"
          style="max-width: 50%"
        ></b-img>
        <br />
        <h5>Video</h5>
        <video controls :src="url+schedule.video" style="max-width: 50%"></video>
      </div>
    </b-container>
  </div>
</template>
<script>
import { ENV } from '@/config/env.js' 

import UpdateScheduleFootBall from "./UpdateScheduleFootBall";
import UpdateScheduleTableTennis from "./UpdateScheduleTableTennis";
import UpdateScheduleBasketBall from "./UpdateScheduleBasketBall";
export default {
  data() {
    return {
      url:ENV.BASE_IMAGE,
      dialogFootball: false,
      dialogTableTennis: false,
      dialogBasketBall: false,
      schedule: [],
      team1: [],
      team2: [],
      inforTeam1: [],
      inforTeam2: [],
      type: "",
      itemlinks: [
        {
          text: "Dashboard",
          disabled: false,
          href: "/admin/home",
        },
        {
          text: "Schedule",
          disabled: false,
          href: "/LayoutSchedule",
        },
      ],
    };
  },
  components: {
    UpdateScheduleFootBall,
    UpdateScheduleTableTennis,
    UpdateScheduleBasketBall,
  },
  created() {
    this.getData();
  },
  mounted() {},
  methods: {
    detailTour(item){
      this.$router.push("/DetailTournament/"+item)
    },
    hideModal() {
      this.dialogFootball = false;
      this.dialogTableTennis = false;
      this.dialogBasketBall = false;
    },
    detailMember(idMember) {
      this.$store.dispatch("user/findByEmailUser", idMember).then((res) => {
        console.log(res.data);
        this.$router.push("/DetailUser/" + res.data.id);
      });
    },
    getData() {
      this.$store
        .dispatch("schedule/getById", this.$route.params.id)
        .then((response) => {
          this.schedule = response.data;

          this.$store
            .dispatch("team/teamTourHistory", {
              idTour: response.data.idTour,
              idTeam: response.data.idTeam1,
            })
            .then((response) => {
              this.team1 = response.data;
            });
          this.$store
            .dispatch("team/teamTourHistory", {
              idTour: response.data.idTour,
              idTeam: response.data.idTeam2,
            })
            .then((response) => {
              this.team2 = response.data;
            });
          this.$store
            .dispatch("team/getDetail", {
              params: {
                idTeam: response.data.team[0].idTeam,
                idTour: response.data.idTour,
              },
            })
            .then((response) => {
              this.inforTeam1 = response.data;
            });
          this.$store
            .dispatch("team/getDetail", {
              params: {
                idTeam: response.data.team[1].idTeam,
                idTour: response.data.idTour,
              },
            })
            .then((response) => {
              this.inforTeam2 = response.data;
            });
        });
    },
  },
  watch: {
    schedule() {
      if(this.itemlinks.length!=3){
      this.itemlinks.push({
        text: this.schedule.title,
        disabled: true,
      });
      }
    },
  },
};
</script>