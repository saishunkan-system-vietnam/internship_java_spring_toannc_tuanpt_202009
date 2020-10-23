<template>
  <div style="background-color: white; width: 600px">
    <div>
      <v-icon v-if="this.type == 'Football'" large color="green darken-2">
        mdi-soccer
      </v-icon>
      <v-icon v-if="this.type == 'TableTennis'" large color="blue darken-2">
        sports_tennis
      </v-icon>

      <v-icon v-if="this.type == 'Basketball'" large color="blue darken-2">
        sports_basketball
      </v-icon>
      <span style="cursor: pointer">
        <a
          :href="
            'http://localhost:8080/DetailTournament' +
            this.type +
            '/' +
            data.idTour
          "
          @click="windowClose"
          target="_blank"
          >{{ data.nameTour }}</a
        >
      </span>
      <span
        style="
          float: right;
          font-size: 12px;
          margin-top: 10px;
          margin-right: 40px;
        "
        >{{ data.timeStart }}
        <v-icon small color="green darken-2">mdi-arrow-right </v-icon
        >{{ data.timeEnd }}
      </span>
    </div>
    <div>
      <v-row>
        <v-col class="text-center"
          ><b-img
            center
            :src="!!data ? data.team[0].logo : ''"
            style="width: 100px"
            alt="Center image"
          ></b-img>
          <button>
            <a
              :href="
                'http://localhost:8080/DetailTeamFootball/' +
                data.team[0].idTeam
              "
              @click="windowClose"
              target="_blank"
              >{{ !!data ? data.team[0].nameTeam : "" }}</a
            >
          </button>
        </v-col>
        <v-col class="text-center" style="margin-top: 10px"
          ><div v-if="data.status == 0">
            <h1>?-?</h1>
            Upcomming
          </div>
          <div v-if="data.status == 1">
            <h1>?-?</h1>
            On game
          </div>
          <div v-if="data.status == 2">
            <h1>
              {{ data.video == null ? "" : data.scoreTeam1 }} -
              {{ data.video == null ? "" : data.scoreTeam2 }}
            </h1>
            Finished
          </div></v-col
        >
        <v-col class="text-center"
          ><b-img
            center
            :src="!!data ? data.team[1].logo : ''"
            style="width: 100px"
            alt="Center image"
          ></b-img>
          <button class="text-center">
            <a
              :href="
                'http://localhost:8080/DetailTeamFootball/' +
                data.team[1].idTeam
              "
              target="_blank"
              @click="windowClose"
            >
              {{ !!data ? data.team[1].nameTeam : "" }}</a
            >
          </button>
        </v-col>
      </v-row>
    </div>
    <div>
      <v-card>
        <v-tabs v-model="tab" dark flat>
          <v-tabs-slider></v-tabs-slider>

          <v-tab href="#tab-1"> Match </v-tab>

          <v-tab href="#tab-2"> Related match </v-tab>

          <v-tab href="#tab-3"> Photo</v-tab>
          <v-tab href="#tab-4"> Video </v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item :value="'tab-' + 1">
            <v-card flat>
              <v-card-text>
                <v-card>
                  <v-tabs v-model="tabb">
                    <v-tabs-slider></v-tabs-slider>

                    <v-tab href="#tabb-1"> Match information </v-tab>

                    <v-tab href="#tabb-2"> Player </v-tab>

                    <v-tab href="#tabb-3">Match history </v-tab>
                  </v-tabs>

                  <v-tabs-items v-model="tabb">
                    <!-- Thông tin trận đấu -->
                    <v-tab-item :value="'tabb-1'">
                      <v-card flat>
                        <v-card-text>
                          <div
                            class="text-center"
                            style="background-color: #bcaaa4"
                          >
                            Score
                          </div>
                          <table class="table">
                            <tbody>
                              <tr
                                v-for="(item, index) in data.round"
                                :key="index"
                              >
                                <td>
                                  {{ item.roundName }}
                                </td>
                                <td>
                                  {{
                                    data.video == null ? "" : item.roundScore1
                                  }}
                                </td>
                                <td>
                                  {{
                                    data.video == null ? "" : item.roundScore2
                                  }}
                                </td>
                              </tr>
                            </tbody>
                          </table>

                          <div
                            class="text-center"
                            style="background-color: #bcaaa4"
                          >
                            Match information
                          </div>
                          <b> Competition Venue</b> :{{ data.address }}
                          <br />
                          <b>Time</b>:{{ data.timeStart }}
                          <div
                            class="text-center"
                            style="background-color: #bcaaa4"
                          >
                            Statistics index
                          </div>
                          <v-row>
                            <v-col cols="12" sm="6"></v-col>
                              <v-col cols="12" sm="3" class="text-center"><b>{{
                                    !!data ? data.team[0].nameTeam : ""
                                  }}</b></v-col>
                               <v-col cols="12" sm="3" class="text-center"><b>{{
                                    !!data ? data.team[1].nameTeam : ""
                                  }}</b></v-col>
                          </v-row>
                          <v-row>
                            <v-col cols="12" sm="6">
                              <ul>
                                <li>Win rate</li>
                                <li>Matches in the tournament</li>
                                <li>Number won in the tournament</li>
                                <li>Join the match number</li>
                                <li>number of wins</li>
                              </ul>
                            </v-col>
                            <v-col cols="12" sm="3">
                              <ul style="list-style: none">
                                <li style="text-align: center">
                                  {{ inforTeam1.rate }}%
                                </li>
                                <li style="text-align: center">
                                  {{ inforTeam1.sumJoinByTour }}
                                </li>
                                <li style="text-align: center">
                                  {{ inforTeam1.sumWinJoinByTour }}
                                </li>
                                <li style="text-align: center">
                                  {{ inforTeam1.sum }}
                                </li>
                                <li style="text-align: center">
                                  {{ inforTeam1.sumWin }}
                                </li>
                              </ul>
                            </v-col>
                            <v-col cols="12" sm="3">
                              <ul style="list-style: none">
                                <li style="text-align: center">
                                  {{ inforTeam2.rate }}%
                                </li>
                                <li style="text-align: center">
                                  {{ inforTeam2.sumJoinByTour }}
                                </li>
                                <li style="text-align: center">
                                  {{ inforTeam2.sumWinJoinByTour }}
                                </li>
                                <li style="text-align: center">
                                  {{ inforTeam2.sum }}
                                </li>
                                <li style="text-align: center">
                                  {{ inforTeam2.sumWin }}
                                </li>
                              </ul>
                            </v-col>
                          </v-row>
                          <div
                            class="text-center"
                            style="background-color: #bcaaa4"
                          >
                            Description
                          </div>
                          <v-textarea
                            name="input-7-1"
                            :value="data.description"
                            disabled
                          ></v-textarea>
                          <div
                            class="text-center"
                            style="background-color: #bcaaa4"
                          >
                            Comment
                          </div>
                          <v-textarea
                            v-model="textComment"
                            style="margin-top: 20px"
                            solo
                            name="input-7-4"
                            height="30px"
                            placeholder="comment"
                          ></v-textarea>
                          <v-btn
                            depressed
                            color="success"
                            @click="commentSubmit"
                          >
                            comment
                          </v-btn>
                          <v-card>
                            <v-card-text>
                              <div v-for="(item, i) in comment" :key="i">
                                <template v-if="i < number">
                                  <p>{{ item.nameProfile }}</p>
                                  <li>{{ item.text }}</li>
                                </template>
                              </div>
                              <div
                                class="text-center"
                                v-if="comment.length > 5"
                              >
                                <a style="color: blue">Hiển thị thêm</a>
                              </div>
                            </v-card-text>
                          </v-card>
                        </v-card-text>
                      </v-card>
                    </v-tab-item>
                    <!-- Thống kê cầu thủ -->
                    <v-tab-item :value="'tabb-2'">
                      <v-card flat>
                        <v-card-text>
                          <h3 style="color: blue">{{ team1.nameTeam }}</h3>

                          <v-simple-table>
                            <template v-slot:default>
                              <thead>
                                <tr>
                                  <th class="text-left">Avatar</th>
                                  <th class="text-left">Name</th>
                                  <th class="text-left">Team</th>
                                  <th class="text-left">Address</th>
                                  <th class="text-left">Detail</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr
                                  v-for="(item, index) in team1.profile"
                                  :key="index"
                                  @click="detailMember(item)"
                                >
                                  <td>
                                    <v-avatar style="margin: 5px">
                                      <img :src="item.avatar" alt="John" />
                                    </v-avatar>
                                  </td>
                                  <td>{{ item.name }}</td>
                                  <td>{{ team1.nameTeam }}</td>
                                  <td>{{ item.address }}</td>
                                  <td>
                                    <a
                                      :href="
                                        'http://localhost:8080/PlayerProfile/' +
                                        item.id
                                      "
                                      target="_blank"
                                      @click="windowClose"
                                    >
                                      <v-icon> mdi-chevron-triple-right</v-icon>
                                    </a>
                                  </td>
                                </tr>
                              </tbody>
                            </template>
                          </v-simple-table>
                          <h3 style="color: blue; margin-top: 50px">
                            {{ team2.nameTeam }}
                          </h3>
                          <v-simple-table>
                            <template v-slot:default>
                              <thead>
                                <tr>
                                  <th class="text-left">Avatar</th>
                                  <th class="text-left">Name</th>
                                  <th class="text-left">Team</th>
                                  <th class="text-left">Address</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr
                                  v-for="(item, index) in team2.profile"
                                  :key="index"
                                >
                                  <td>
                                    <v-avatar style="margin: 5px">
                                      <img :src="item.avatar" alt="John" />
                                    </v-avatar>
                                  </td>
                                  <td>{{ item.name }}</td>
                                  <td>{{ team2.nameTeam }}</td>
                                  <td>{{ item.address }}</td>
                                </tr>
                              </tbody>
                            </template>
                          </v-simple-table>
                        </v-card-text>
                      </v-card>
                    </v-tab-item>
                    <v-tab-item :value="'tabb-3'">
                      <v-card>
                        <v-card-text>
                          <div>
                            <b-card no-body>
                              <b-tabs>
                                <b-tab
                                  :title="item.roundName"
                                  v-for="(item, index) in data.round"
                                  v-bind:key="index"
                                >
                                  <b-card-text>
                                    <h3>Score :</h3>
                                    <b-row>
                                      <b-col class="text-center"
                                        ><b-img
                                          :src="team1.logo"
                                          size="6rem"
                                        ></b-img
                                      ></b-col>
                                      <b-col class="text-center"
                                        ><h1>
                                          {{
                                            data.video == null
                                              ? ""
                                              : item.roundScore1
                                          }}-{{
                                            data.video == null
                                              ? ""
                                              : item.roundScore2
                                          }}
                                        </h1></b-col
                                      >
                                      <b-col class="text-center"
                                        ><b-img
                                          :src="team2.logo"
                                          size="6rem"
                                        ></b-img
                                      ></b-col>
                                    </b-row>
                                    Reported :
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
                        </v-card-text>
                      </v-card>
                    </v-tab-item>
                  </v-tabs-items>
                </v-card>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item :value="'tab-' + 2">
            <v-card flat style="min-height: 500px">
              <v-card-text>
                <v-simple-table>
                  <template v-slot:default>
                    <tbody>
                      <tr
                        style="cursor: pointer"
                        v-for="(item, index) in related"
                        color="primary"
                        :key="index"
                        v-b-popover.hover.bottom="'Click to see details'"
                        @click="relatedMatch(item.idSchedule)"
                      >
                        <td
                          :style="
                            item.status == 0
                              ? 'color:green'
                              : item.status == 1
                              ? 'color:blue'
                              : 'color:red'
                          "
                        >
                          {{
                            item.status == 0
                              ? "UPCOMMING"
                              : item.status == 1
                              ? "ON GAME"
                              : "FINSISHED"
                          }}
                        </td>
                        <td>
                          {{ !!data ? item.team[0].nameTeam : "" }}
                        </td>
                        <td>
                          {{
                            item.status == 2 && item.video != null
                              ? item.scoreTeam1
                              : " "
                          }}
                          -
                          {{
                            item.status == 2 && item.video != null
                              ? item.scoreTeam2
                              : " "
                          }}
                        </td>
                        <td>
                          {{ !!data ? item.team[1].nameTeam : "" }}
                        </td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item :value="'tab-' + 3">
            <v-card flat style="min-height: 500px">
              <v-card-text class="text-center">
                <v-img
                  lazy-src="https://www.westernheights.k12.ok.us/wp-content/uploads/2020/01/No-Photo-Available.jpg"
                  max-height="150"
                  max-width="250"
                  :src="data.image"
                ></v-img>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item :value="'tab-' + 4">
            <v-card flat style="min-height: 500px">
              <v-card-text>
                <div v-if="data.video != null">
                  <video controls :src="data.video" width="550px"></video>
                </div>
                <b-img
                  src="https://torshizitrade.com/wp-content/uploads/2019/01/no-video.jpg"
                ></b-img>
              </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-card>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tab: null,
      data: "",
      tabb: null,
      inforTeam1: "",
      inforTeam2: "",
      team1: "",
      team2: "",
      related: "",
      type: "",
      number: 5,
      textComment: "",
      comment: [],
    };
  },
  created() {
    this.$store
      .dispatch("schedule/getById", this.$route.params.id)
      .then((response) => {
        this.data = response.data;
        console.log(this.data);
        this.type = response.data.team[0].type;
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
              idTeam: response.data.team[1].idTeam,
              idTour: response.data.idTour,
            },
          })
          .then((response) => {
            this.inforTeam2 = response.data;
          });

        this.$store
          .dispatch("schedule/related", {
            idTour: response.data.idTour,
            idTeam1: response.data.idTeam1,
            idTeam2: response.data.idTeam2,
          })
          .then((response) => {
            this.related = response.data;
          });
        this.$store
          .dispatch("comment/getAll", this.$route.params.id)
          .then((response) => {
            this.comment = response.data;
          });
      });
  },
  methods: {
    getComment() {
      this.$store
        .dispatch("comment/getAll", this.$route.params.id)
        .then((response) => {
          this.comment = response.data;
        });
    },
    relatedMatch(id) {
      var myWindow = window.open(
        "http://localhost:8080/detail/" + id,
        "myWindow",
        "width=600px,height=600"
      );
    },

    commentSubmit() {
      if (
        this.$store.state.user.userInfo == null ||
        this.$store.state.user.userInfo.profile.name == null ||
        this.$store.state.user.userInfo.profile.name == ""
      ) {
        alert("need to login");
      } else {
        if (this.textComment != "") {
          this.$store
            .dispatch("comment/addComment", {
              text: this.textComment,
              idSchedule: this.data.idSchedule,
              nameProfile: this.$store.state.user.userInfo.profile.name,
            })
            .then((response) => {
              this.getComment();
              this.textComment = "";
            });
        }
      }
    },
    windowClose() {
      window.close();
    },
  },
  watch: {},
};
</script>