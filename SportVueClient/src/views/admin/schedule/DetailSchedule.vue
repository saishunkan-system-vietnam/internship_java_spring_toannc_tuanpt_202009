<template>
  <div>
    <div>
      <div v-if="schedule.status == 2">
        <div v-if="(team1.type == 'Football')">
          <v-btn color="green darken-1" text @click="dialogFootball = true">
            <b-icon-upload></b-icon-upload>upload
          </v-btn>
        </div>
        <div v-else>
          <v-btn
            color="green darken-1"
            text
            @click="dialogTableTennis = true"
          ><b-icon-upload></b-icon-upload>upload</v-btn>
        </div>
      </div>
      <div v-else>
        <p class="text-danger">Giải đấu chưa kết thúc</p>
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
      <h1 style="color: red">Giải Đấu :{{ schedule.nameTour }}</h1>

      <b-row>
        <b-col>
          <h3 style="color: Blue">Team 1: {{ team1.nameTeam }}</h3>
          <b-img :src="team1.logo" fluid alt="Responsive image"></b-img>

          <h4 style="color: Blue">Thành viên :</h4>
          <ol v-for="(item, index) in team1.profile" v-bind:key="index">
            <li>
              {{ item.name }} - {{ item.age }} year old -{{ item.address }}
            </li>
          </ol>
          <h4 style="color: Blue">Thông số kỹ thuật :</h4>
          <ul>
            <li>Tỉ lệ thắng : {{ inforTeam1.rate }}%</li>
            <li>Tổng số trận trong giải : {{ inforTeam1.sumJoinByTour }}</li>
            <li>
              Tổng số trận thắng trong giải: {{ inforTeam1.sumWinJoinByTour }}
            </li>
            <li>Tổng số trận tham gia : {{ inforTeam1.sum }}</li>
            <li>Tổng số trận thắng : {{ inforTeam1.sumWin }}</li>
          </ul>
        </b-col>
        <b-col>
          <h3 style="color: Blue">Team 2: {{ team2.nameTeam }}</h3>
          <b-img :src="team1.logo" fluid alt="Responsive image"></b-img>

          <h4 style="color: Blue">Thành viên :</h4>
          <ol v-for="(item, index) in team2.profile" v-bind:key="index">
            <li>
              {{ item.name }} - {{ item.age }} year old -{{ item.address }}
            </li>
          </ol>
          <h4 style="color: Blue">Thông số kỹ thuật :</h4>
          <ul>
            <li>Tỉ lệ thắng : {{ inforTeam1.rate }}%</li>
            <li>Tổng số trận trong giải : {{ inforTeam2.sumJoinByTour }}</li>
            <li>
              Tổng số trận thắng trong giải: {{ inforTeam2.sumWinJoinByTour }}
            </li>
            <li>Tổng số trận tham gia : {{ inforTeam2.sum }}</li>
            <li>Tổng số trận thắng : {{ inforTeam2.sumWin }}</li>
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
                    <b-col
                      ><b-avatar :src="team1.logo" size="6rem"></b-avatar
                    ></b-col>
                    <b-col
                      ><h1>
                        {{ item.roundScore1 }}-{{ item.roundScore2 }}
                      </h1></b-col
                    >
                    <b-col
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
      <h2 style="color: blue">Tổng kết</h2>
      <b-form-textarea
        id="textarea-no-resize"
        placeholder="Description"
        rows="5"
        no-resize
        v-model="schedule.description"
        disabled
      ></b-form-textarea>
      <h4>
        Tỉ số chung cuộc: {{ schedule.scoreTeam1 }}-{{ schedule.scoreTeam2 }}
      </h4>
      <div class="text-center">
        <h3 style="color: Blue">Ảnh và video trận đấu</h3>
        <h5>Ảnh</h5>
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
          console.log(this.schedule);
          this.$store
            .dispatch("team/teamTourHistory", {
              idTour: response.data.idTour,
              idTeam: response.data.idTeam1,
            })
            .then((response) => {
              this.team1 = response.data;
              ;
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