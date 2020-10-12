<template>
  <div>
    <div>
      <div v-if="schedule.status == 2">
        <div v-if="team1.type == 'Football'">
          <v-btn color="green darken-1" text @click="dialogFootball = true">
            <b-icon-upload></b-icon-upload>upload
          </v-btn>
        </div>
        <div v-else>
          <v-btn color="green darken-1" text @click="dialogTableTennis = true"
            ><b-icon-upload></b-icon-upload>upload</v-btn
          >
        </div>
      </div>
      <div v-else>
        <p class="text-danger">The tournament is not over yet</p>
      </div>
    </div>
    <v-row justify="center">
      <v-dialog v-model="dialogFootball" width="900px">
        <v-card>
          <v-card-title class="headline"> Upload data </v-card-title>
          <v-card-text>
            <UploadScheduleFootBall
              :schedule="schedule"
              :loadData="getAll"
              :callback="hideModal"
            />
          </v-card-text>
        </v-card>
      </v-dialog>
      <v-dialog v-model="dialogTableTennis" width="900px">
        <v-card>
          <v-card-title class="headline"> Upload data </v-card-title>
          <v-card-text>
            <UploadScheduleTableTennis
              :schedule="schedule"
              :loadData="getAll"
              :callback="hideModal"
            />
          </v-card-text>
        </v-card>
      </v-dialog>
    </v-row>
    <b-container>
      <h1 style="color: red">Tournament :{{ schedule.nameTour }}</h1>

      <b-row>
        <b-col>
          <h3 style="color: Blue">Team 1: {{ team1.nameTeam }}</h3>
          <b-img :src="team1.logo" fluid alt="Responsive image"></b-img>

          <h4 style="color: Blue">Member :</h4>
          <ol v-for="(item, index) in team1.profile" v-bind:key="index">
            <li>
              {{ item.name }} - {{ item.age }} year old -{{ item.address }}
            </li>
          </ol>
          <h4 style="color: Blue">Technical specifications :</h4>
          <ul>
            <li>Win rate : {{ inforTeam1.rate }}%</li>
            <li>
              Total number of games in the tournament :
              {{ inforTeam1.sumJoinByTour }}
            </li>
            <li>
              Total number of wins in the tournament:
              {{ inforTeam1.sumWinJoinByTour }}
            </li>
            <li>Total number of games participating : {{ inforTeam1.sum }}</li>
            <li>Total number of wins : {{ inforTeam1.sumWin }}</li>
          </ul>
        </b-col>
        <b-col>
          <h3 style="color: Blue">Team 2: {{ team2.nameTeam }}</h3>
          <b-img :src="team1.logo" fluid alt="Responsive image"></b-img>

          <h4 style="color: Blue">Member:</h4>
          <ol v-for="(item, index) in team2.profile" v-bind:key="index">
            <li>
              {{ item.name }} - {{ item.age }} year old -{{ item.address }}
            </li>
          </ol>
          <h4 style="color: Blue">Technical specifications :</h4>
          <ul>
            <li>Win rate : {{ inforTeam1.rate }}%</li>
            <li>
              Total number of games in the tournament :
              {{ inforTeam2.sumJoinByTour }}
            </li>
            <li>
              Total number of wins in the tournament:
              {{ inforTeam2.sumWinJoinByTour }}
            </li>
            <li>Total number of games participating : {{ inforTeam2.sum }}</li>
            <li>Total number of wins: {{ inforTeam2.sumWin }}</li>
          </ul>
        </b-col>
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
                  Tỉ số :
                  <b-row>
                    <b-col class="text-center"
                      ><b-avatar :src="team1.logo" size="6rem"></b-avatar
                    ></b-col>
                    <b-col class="text-center"
                      ><h1>
                        {{ item.roundScore1 }}-{{ item.roundScore2 }}
                      </h1></b-col
                    >
                    <b-col class="text-center"
                      ><b-avatar :src="team1.logo" size="6rem"></b-avatar
                    ></b-col>
                  </b-row>
                  Diễn biến :
                  <b-form-textarea
                    id="textarea-no-resize"
                    placeholder="Description"
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
        placeholder="Description"
        rows="5"
        no-resize
        v-model="schedule.description"
        disabled
      ></b-form-textarea>
      <h4>Final score : {{ schedule.scoreTeam1 }}-{{ schedule.scoreTeam2 }}</h4>
      <div class="text-center">
        <h3 style="color: Blue">Match photos and videos</h3>
        <h5>Photos</h5>
        <b-img
          :src="schedule.image"
          alt="Responsive image"
          style="max-width: 50%"
        ></b-img>
        <br />
        <h5>Video</h5>
        <video controls :src="schedule.video" style="max-width: 50%"></video>
      </div>
    </b-container>
  </div>
</template>
<script>
import UploadScheduleFootBall from "./UploadScheduleFootBall";
import UploadScheduleTableTennis from "./UploadScheduleTableTennis";
export default {
  data() {
    return {
      dialogFootball: false,
      dialogTableTennis: false,

      schedule: [],
      team1: [],
      team2: [],
      inforTeam1: [],
      inforTeam2: [],
      type: "",
    };
  },
  components: {
    UploadScheduleFootBall,
    UploadScheduleTableTennis,
  },
  created() {
    this.getAll();
  },
  methods: {
    hideModal() {
      this.dialogFootball = false;
      this.dialogTableTennis = false;
    },
    getAll() {
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
};
</script>